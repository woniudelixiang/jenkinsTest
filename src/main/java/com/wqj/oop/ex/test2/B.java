package com.wqj.oop.ex.test2;

public class B extends A{
	
	public void show(){
		System.out.println(a);
		a++;
		super.show();
	}
}
