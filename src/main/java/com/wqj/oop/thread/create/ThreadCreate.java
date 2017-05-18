package com.wqj.oop.thread.create;

public class ThreadCreate {

	public static void main(String[] args) {
		
		// 方式一：继承Thread类
//		test1();
		
		// 方式二： 实现Runnable接口
//		test2();
		
		test3();
	}
	
	/*
	 * 方式一：继承Thread类
	 */
	public static void test1(){
		Thread t1 = new Thread(){
			@Override
			public void run() {
				System.out.println("继承Thread类  ThreadName:  " + Thread.currentThread().getName());
			}
		};
		
		t1.start();
	}
	
	/*
	 * 方式二： 实现Runnable接口
	 */
	public static void test2(){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("实现Runnable接口  ThreadName:  " + Thread.currentThread().getName());
			}
		});
		
		t1.start();
	}
	
	
	public static  void test3(){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("实现Runnable接口  ThreadName:  " + Thread.currentThread().getName());
			}
		}){
			@Override
			public void run() {
				System.out.println("继承Thread类  ThreadName:  " + Thread.currentThread().getName());
			}
		};
		
		t1.start();
	}
	
	
	
	
	
}




