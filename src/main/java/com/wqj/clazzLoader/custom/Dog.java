package com.wqj.clazzLoader.custom;

public class Dog {

	public static int d1 = 5;
	
	public Dog(){
		System.out.println("Dog is loader by: " + this.getClass().getClassLoader());
	}
}
