package com.wqj.clazzLoader.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{

	// 类加载器的名字
	private String name;
	
	// 指定加载的目录
	private String path = "E:\\loadPath\\";
	
	// 加载的文件类型
	private final String fileType = ".class";
	
	public MyClassLoader(String name) {
		super();  //  让系统类加载器作为当前类加载器的父类加载器
		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String name) {
		super(parent);  //  让指定的类加载器作为当前类加载器的父类加载器
		this.name = name;
	}

	/**
	 * 根据参数指定的类的名字，返回对应的Class对象的引用
	 * @param name  类的全限定名
	 *  @return  对应类的Class对象的引用
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}
	
	/**
	 * 根据类名获取类的二进制字节数组
	 * @param name  类的全限定名
	 * @return  对应类的二进制字节数组
	 */
	private byte[] loadClassData(String name){
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		try {
			is = new FileInputStream(new File(path + name.replace(".", "\\") + fileType));
			baos = new ByteArrayOutputStream();
			
			int ch = 0;
			while(-1 != (ch = is.read())){
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != is){
					is.close();
				}
				if(null != baos){
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileType() {
		return fileType;
	}
	
	
	public static void main(String[] args) {
		MyClassLoader loader1 = new MyClassLoader("loader1");
		loader1.setPath("E:\\loadPath\\serverlib\\");
		
		MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
		loader2.setPath("E:\\loadPath\\clientlib\\");
		
		MyClassLoader loader3 = new MyClassLoader(null, "loader3");
		loader3.setPath("E:\\loadPath\\otherlib\\");
		
		try {
			test(loader1);
//			test(loader2);
//			test(loader3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void test(ClassLoader loader) throws Exception{
		Class<?> clazz = loader.loadClass("com.wqj.clazzLoader.custom.Sample");
		clazz.newInstance();
		
	}
	
	public static void test4(ClassLoader loader) throws Exception{
		Class<?> clazz = loader.loadClass("com.wqj.clazzLoader.custom.Sample");
		Object obj = clazz.newInstance();
		
		Sample sample = (Sample)obj;
		System.out.println("=====>>>>>>> v1: "+ sample.v1);
		System.out.println("v1: "+ Sample.v1);
		
	}
	
}
