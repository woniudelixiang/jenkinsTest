package com.wqj.oop.ex.test1;

public class Test1 {

	/**
	 * 把这个类加载到JVM上，会先生成类的实例，但是new关键字有一个特殊的性质就是会先调用类的构造方法，
	 * 如果这个类继承了父类，则子类实例化时，会先调用父类的构造方法
	 * @param args
	 */
	public static void main(String[] args) {
		B b=new B();
	}
	
	
}
