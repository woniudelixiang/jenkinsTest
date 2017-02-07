package com.wqj.clazzLoader.init;

public class Test4 {
	public static void main(String[] args) {
		System.out.println(Child1.b);  
	}
}

class Parent1{
	
	public static int b = 3;
	
	static{
		System.out.println("Parent1 static block");  // 1
	}
}

class Child1 extends Parent1{
	public static int b = 4;
	
	static{
		System.out.println("Child1 static block");  //2
	}
}
