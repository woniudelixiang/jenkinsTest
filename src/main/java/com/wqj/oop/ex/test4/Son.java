package com.wqj.oop.ex.test4;

public class Son extends Father {
	public int a = 1;
	public int b = 2;

	public Son() {
		// 隐式调用super();
//		super();
		System.out.println(this);
	}
}
