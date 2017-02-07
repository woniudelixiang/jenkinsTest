package com.wqj.clazzLoader.init;

// 被加载的类
class Test{
	static { 
		System.out.println("静态初始化块执行了！"); 
	} 
}

public class LoaderTest {

	public static void main(String[] args) throws ClassNotFoundException{ 
		// 获得系统类加载器
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		System.out.println(loader); 
		//使用ClassLoader.loadClass()来加载类，不会执行初始化块 
		loader.loadClass("com.wqj.clazzLoader.init.Test"); 

		//使用Class.forName()来加载类，默认会执行初始化块 
//		Class.forName("com.wqj.clazzLoader.init.Test"); 

		//使用Class.forName()来加载类，并指定ClassLoader，可以指定是否初始化 
//		Class.forName("com.wqj.clazzLoader.init.Test", false, loader); 

	}
}
