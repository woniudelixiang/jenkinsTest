package com.wqj.clazzLoader.init;

public class Test1 {
	
	static {
		System.out.println("-----------");
		 i = 2;
	}
	
	private static int i = 1;
	
	public static int getI(){
		return i;
	}

	public static void main(String[] args) {
		System.out.println(Test1.getI());
	}
	
}
