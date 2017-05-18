package com.wqj.oop.ex.test4;

public class Father {

	public int a = 10;

	public Father() {
		System.out.println(this instanceof Son);
		System.out.println(this.a);
		System.out.println(((Son) this).a);
		System.out.println(this.getClass().getName());
	}
	
}
