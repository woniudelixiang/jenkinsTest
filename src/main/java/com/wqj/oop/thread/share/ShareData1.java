package com.wqj.oop.thread.share;

/**
 * 设计4个线程，其中2个线程每次对j进行加1，另外2个线程每次对j进行减1
 * 
 */
public class ShareData1{
	int j = 0;
	
	public synchronized  void increase(){
		j = j + 1;
		sys();
	}
	
	public synchronized void decrease(){
		j = j - 1;
		sys();
	}

	private void sys(){
		System.out.println(j);
	}
}
