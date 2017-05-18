package com.wqj.oop.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		final BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
		
		for (int i = 1; i <= 2; i++) {
			
			new Thread(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep((long) (Math.random() * 10000));
							System.out.println(Thread.currentThread().getName() + "准备放数据");
							blockingQueue.put(1);  //把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续
							System.out.println(Thread.currentThread().getName() + "放数据成功" + "当前队列有" + blockingQueue.size() + "个数据");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println(Thread.currentThread().getName() + "准备取数据!");
						blockingQueue.take(); // 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入
						System.out.println(Thread.currentThread().getName() + "取数据成功" + "当前队列有" + blockingQueue.size() + "个数据");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
