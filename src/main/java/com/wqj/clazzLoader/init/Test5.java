package com.wqj.clazzLoader.init;

public class Test5 {

	public static void main(String[] args) {
		System.out.println(Child2.b);
		
	}
}


class Parent2{
	
	public static int b = 3;
	
	static{
		System.out.println("Parent2 static block");
	}
}

class Child2 extends Parent1{
	public static int a = 4;
	
	static{
		System.out.println("Child2 static block");
	}
}