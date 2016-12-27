package com.wqj.redis.controller;

public class B extends A{

	private String a  = "aaa";

	public B() {
		super();
	}



	public static void main(String[] args) {
		B b = new B();
		try {
			System.out.println(b.a);
			
			b.isNull();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
