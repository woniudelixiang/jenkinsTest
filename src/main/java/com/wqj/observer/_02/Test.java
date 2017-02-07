package com.wqj.observer._02;

public class Test {
	
	public static void main(String[] args) {
		Dad dad = new Dad();
		Child baby = new Child(dad);
		new Thread(baby).start();
	}
	
}
