package com.wqj.oop.ex.test2;

public class A {
	public int a = 0;
	
	public void show(){
		System.out.println(this.a);  // 在new B() 的时候，该方法会在B的内存中开辟空间，在B中的这个this就是指向B的对象
	}
			
}
