package com.wqj.proxy.defined;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import com.wqj.proxy.People;

import sun.misc.ProxyGenerator;
import sun.reflect.misc.ReflectUtil;

public class MyProxy {

	private final static String MyProxyClassNamePrefix = "$MyProxy";

	protected InvocationHandler h;

	private static Map<ClassLoader, Map<List<String>, Object>> loaderToCache = new WeakHashMap<>();
	private static Object pendingGenerationMarker = new Object();
	private static long nextUniqueNumber = 0;
	private static Object nextUniqueNumberLock = new Object();
	private static Map<Class<?>, Void> MyProxyClasses = Collections.synchronizedMap(new WeakHashMap<Class<?>, Void>());
	private final static Class[] constructorParams = { InvocationHandler.class };
	
	
	public static void main(String[] args) {
		MyProxy.newMyProxyInstance(MyProxy.class.getClassLoader(), new Class[]{People.class}, new ZhangSanInvocationHandler());
		System.out.println("-----------------------------------------------");
	}
	
	private MyProxy() {

	}

	protected MyProxy(InvocationHandler h) {
		this.h = h;
	}
	
	public static Object newMyProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException{
		if (h == null) {
			throw new NullPointerException();
		}
		
//		Class<?> cl = MyProxy.class;
		Class<?> cl = getMyProxyClass0(loader, interfaces);
		
		try {
//			Constructor<?> cons = cl.getDeclaredConstructor(InvocationHandler.class);
//			 Constructor<?> cons= cl.getConstructor(InvocationHandler.class);
			 
//			 final InvocationHandler ih = h;
//			 return newInstance(cons, ih);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


	private static Object newInstance(Constructor<?> cons, InvocationHandler h) {
		try {
			return cons.newInstance(new Object[] {h} );
		} catch (IllegalAccessException | InstantiationException e) {
			throw new InternalError(e.toString());
		} catch (InvocationTargetException e) {
			Throwable t = e.getCause();
			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new InternalError(t.toString());
			}
		}
	}


	private static Class<?> getMyProxyClass0(ClassLoader loader, Class<?>... interfaces) {
		if (interfaces.length > 65535) {
			throw new IllegalArgumentException("interface limit exceeded");
		}
		
		Class<?> MyProxyClass = null;
		String[] interfaceNames = new String[interfaces.length];
		Set<Class<?>> interfaceSet = new HashSet<>();

		for (int i = 0; i < interfaces.length; i++) {
			String interfaceName = interfaces[i].getName();
			Class<?> interfaceClass = null;
			try {
				interfaceClass = Class.forName(interfaceName, false, loader);
			} catch (ClassNotFoundException e) {
			}
			
			if (interfaceClass != interfaces[i]) {
				throw new IllegalArgumentException(
						interfaces[i] + " is not visible from class loader");
			}
			
			if (!interfaceClass.isInterface()) {
				throw new IllegalArgumentException(
						interfaceClass.getName() + " is not an interface");
			}

			if (interfaceSet.contains(interfaceClass)) {
				throw new IllegalArgumentException(
						"repeated interface: " + interfaceClass.getName());
			}
			
			interfaceSet.add(interfaceClass);

			interfaceNames[i] = interfaceName;
		}

		List<String> key = Arrays.asList(interfaceNames);

		Map<List<String>, Object> cache;
			cache = loaderToCache.get(loader);
			if (cache == null) {
				cache = new HashMap<>();
				loaderToCache.put(loader, cache);
			}
			
            do {
                Object value = cache.get(key);
                if (value instanceof Reference) {
                    MyProxyClass = (Class<?>) ((Reference) value).get();
                }
                
                if (MyProxyClass != null) {
                    return MyProxyClass;
                } else if (value == pendingGenerationMarker) {
                    try {
                        cache.wait();
                    } catch (InterruptedException e) {
                    }
                    continue;
                } else {
                    cache.put(key, pendingGenerationMarker);
                    break;
                }
            } while (true);

	        try {
	            String MyProxyPkg = null;
	            for (int i = 0; i < interfaces.length; i++) {
	                int flags = interfaces[i].getModifiers();  // 修饰符
	                System.out.println("修饰符: " + Modifier.isPublic(flags));
	                if (!Modifier.isPublic(flags)) {
	                    String name = interfaces[i].getName();
	                    System.out.println("name : " + name );
	                    int n = name.lastIndexOf('.');
	                    String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
	                    System.out.println("pkg : " + pkg );
	                    
	                    if (MyProxyPkg == null) {
	                        MyProxyPkg = pkg;
	                    } else if (!pkg.equals(MyProxyPkg)) {
	                        throw new IllegalArgumentException(
	                            "non-public interfaces from different packages");
	                    }
	                }
	            }

	            if (MyProxyPkg == null) {
	            	 String name = MyProxy.class.getName();
	            	 int n = name.lastIndexOf('.');
	                 String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
	                 MyProxyPkg = pkg;
	            }
	            
	            {
	                long  num = nextUniqueNumber++;
	                
	                String MyProxyName = MyProxyPkg + MyProxyClassNamePrefix + num;
	                
//	                byte[] MyProxyClassFile = ProxyGenerator.generateProxyClass(MyProxyName, interfaces);
	                System.out.println("MyProxyName: " + MyProxyName);
	                byte[] MyProxyClassFile =  createProxyClassFile(MyProxyName, interfaces);
//	                byte[] MyProxyClassFile = ProxyGenerator.generateProxyClass(MyProxyName, new Class[]{People.class});
	                
//	                try {
//	                    MyProxyClass = defineClass0(loader, MyProxyName,
//	                        MyProxyClassFile, 0, MyProxyClassFile.length);
//	                } catch (ClassFormatError e) {
//	                    throw new IllegalArgumentException(e.toString());
//	                }
	            }
	            MyProxyClasses.put(MyProxyClass, null);

	        } finally {
                if (MyProxyClass != null) {
                    cache.put(key, new WeakReference<Class<?>>(MyProxyClass));
                } else {
                    cache.remove(key);
                }
	        }
	        
	        return MyProxyClass;
	}
	
	
	public static byte[] createProxyClassFile(String proxyName, Class<?>[] interfaces){
		try {
			// 第一个参数是代理类的名字，第二个参数是被代理类类型的数组, 返回代理类的字节数组
			byte[] proxyData = ProxyGenerator.generateProxyClass(proxyName, interfaces);
			
			// 字节数组通过流保存到文件中
			// 创建一个输出流，参数表示输入文件的名字
			OutputStream output = new FileOutputStream(proxyName + ".class");
			output.write(proxyData);
			output.close();
			return proxyData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	 private static native Class defineClass0(ClassLoader loader, String name,
             byte[] b, int off, int len);
	}
