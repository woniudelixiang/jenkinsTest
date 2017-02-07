package com.wqj.clazzLoader;

public class SingletonTest {

	public static void main(String[] args) {
		Singleton instance = Singleton.getInstance();
		
		System.out.println("count1:  " + instance.count1);
		System.out.println("count2:  " + instance.count2);
		
	}
	
	
}
