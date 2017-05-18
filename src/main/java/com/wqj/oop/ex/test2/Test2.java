package com.wqj.oop.ex.test2;

public class Test2 {

	/**
	 * new parent()和new sub()的时候分别在内存的堆空间分配了两块区域，这两块区域分别存有a这个成员变量和它的值
	 * @param args
	 */
	public static void main(String[] args) {
//		Parent p = new Parent();
//		Sub s = new Sub();
//		p.show();
//		s.show();
		
		System.out.println("================================");
		
		
		
		/**
		 * 解题知识： 父类中的所有成员会存在于子类对象的内存中，并且父类中的this会引用当前类对象
		 */
		B b = new B();
		b.show();
	}

}
