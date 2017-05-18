package com.wqj.oop.thread.share;

public class MultiThreadShareDataTest {

	
	public static void main(String[] args) {
		final ShareData1 data1 = new ShareData1();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				data1.decrease();
			}
		}).start();
		
		new Thread(new MyRunnable1(data1)).start();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				data1.increase();
			}
		}).start();
		
		new Thread(new MyRunnable2(data1)).start();
	}
	
	
}
