package com.wqj.oop.ex.test5;

import java.util.List;

@SuppressWarnings({ "rawtypes", "unused" })
public abstract class T1 {

	static{
		System.out.println("static T1  1");
	}
	
	{
		System.out.println("T1 1");
	}
	
	
	private List t1List = new MyList("t1 list");
	
	{
		System.out.println("T1 2");
	}
	
	static{
		System.out.println("static T1 2");
	}
	
	public T1(){
		t1();
		System.out.println("construct T1 2");
	}
	
	public abstract void t1();
}
