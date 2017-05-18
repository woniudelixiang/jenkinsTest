package com.wqj.oop.thread.share;

public class MyRunnable2 implements Runnable{

	final ShareData1 data1;
	public MyRunnable2(ShareData1 data1) {
		this.data1 = data1;
	}
	
	@Override
	public void run() {
		data1.increase();
	}

}
