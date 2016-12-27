package com.wqj.reflect.test;

public class B {

	private A a = new A();
	
	private String tag = "";
	private String sbody = "";
	
	public void test1(){
		 tag = "method1";
		 sbody = "sbody1";
		 a.dealWith(tag, sbody);
		 
	}
	
	public  void test2(){
		 tag = "method2";
		 sbody = "sbody2";
		 a.dealWith(tag, sbody);
	}
	
	
	public static void main(String[] args) {
		B b = new B();
		b.test1();
		//b.test1();
	}
}
