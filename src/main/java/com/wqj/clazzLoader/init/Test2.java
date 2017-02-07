package com.wqj.clazzLoader.init;

public class Test2 {
	public static void main(String[] args) {
		System.out.println(FinalTest.x);
	}
	
}


class FinalTest{
	public static final int x = 4/3;
	
	static {
		System.out.println("FinalTest static block");
	}
	
}