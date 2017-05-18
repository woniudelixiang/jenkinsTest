package com.wqj.oop.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunicationTest {
	public static void main(String[] args) {

		new BlockingQueueCommunicationTest().execute();
	}

	private void execute() {
		final Business business = new Business();

		new Thread(new Runnable() {

			public void run() {
				for (int j = 1; j <= 2; j++) {
					business.sub(j);
				}
			}
		}).start();

		for (int j = 1; j <= 2; j++) {
			business.main(j);
		}

	}

	private class Business {

		BlockingQueue blockingQueue1 = new ArrayBlockingQueue(1);
		BlockingQueue blockingQueue2 = new ArrayBlockingQueue(1);

		// 匿名构造方法，先于非匿名构造方法执行
		{
			try {
				blockingQueue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void sub(int j) {
			try {
				blockingQueue1.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 1; i <= 6; i++) {
				System.out.println("sub thread sequece of " + i + ",loop of " + j);
			}
			try {
				blockingQueue2.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		public void main(int j) {
			try {
				blockingQueue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 1; i <= 6; i++) {
				System.out.println("main thread sequece of " + i + ",loop of " + j);
			}
			try {
				blockingQueue1.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}