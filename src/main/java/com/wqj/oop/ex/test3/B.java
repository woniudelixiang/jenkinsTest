package com.wqj.oop.ex.test3;

public class B extends A{

//	public String s = null;
	
	// 累的加载过程不管多么复杂，总是按照-静态代码块(之间循序执行) -> 普通代码块(之间循序执行) -> 构造方法
	public B(){
		super();
		 s = "构造";
	}
	public String s = "自身";
	
	public void init(){
		s = "123";
	}
}
