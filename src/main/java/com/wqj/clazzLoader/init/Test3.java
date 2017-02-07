package com.wqj.clazzLoader.init;

import java.util.Random;

public class Test3 {
	public static void main(String[] args) {
		System.out.println(FinalTest1.x);
	}
}


class FinalTest1{
	public static final int x = new Random().nextInt(100);
	
	static {
		System.out.println("FinalTest1 static block");
	}
	
}