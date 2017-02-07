package com.wqj.observer;

public class Test {

	
	public static void main(String[] args) {
		Child baby = new Child();
		new Thread(baby).start();
		
		Dad dad = new Dad(baby);
		new Thread(dad).start();
	}
	
	
}
