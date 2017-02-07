package com.wqj.clazzLoader.custom;

public class Sample {

	public static int v1=1;

	public Sample() {
		super();
		System.out.println("Sample is loaded by: " + this.getClass().getClassLoader());
		
		// 主动使用Dog
		new Dog();
	}
	
	
	
}
