package com.wqj.proxy;

import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.ProxyGenerator;

public class ProxyTest {

	public static void main(String[] args) {
		try {
			createProxyClassFile();
			
//			Class<?> cl = MyProxy.class;
//			 Constructor<?> cons= cl.getConstructor(InvocationHandler.class);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void createProxyClassFile() throws Exception{
		
		// 第一个参数是代理类的名字，第二个参数是被代理类类型的数组, 返回代理类的字节数组
		byte[] proxyData = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{People.class,Bird.class});
		
		// 字节数组通过流保存到文件中
		// 创建一个输出流，参数表示输入文件的名字
		OutputStream output = new FileOutputStream("$Proxy0.class");
		output.write(proxyData);
		output.close();
		
	}
}
