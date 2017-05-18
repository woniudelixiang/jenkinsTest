package com.wqj.oop.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriterLockTest {
	
	public static void main(String[] args) {
		final ReadWriterLockTest.ReadWrite readWrite = new ReadWriterLockTest().new ReadWrite();

		for (int i = 0; i < 3; i++) {
			// 读线程
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10; j++) {
						readWrite.get();
					}
				}
			}).start();

			// 写线程
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 2; j++) {
						int data = new Random().nextInt(10000);
						readWrite.put(data);
					}
				}
			}).start();
		}

	}

	class ReadWrite {
		private Object data = null; // 共享数据， 只有一个线程

		// 读写锁
		private ReadWriteLock rwl = new ReentrantReadWriteLock();

		public void get() {
			rwl.readLock().lock();  // 读上锁
			try {
				System.out.println(Thread.currentThread().getName() + "我准备读了");
				Thread.sleep(new Random().nextInt(1000));
				System.out.println(Thread.currentThread().getName() + "我读到的值是：" + data);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				rwl.readLock().unlock();  // 读释放锁
			}
		}

		public void put(Object data) {
			rwl.writeLock().lock();  // 写上锁
			try {
				System.out.println(Thread.currentThread().getName() + "我准备写了");
				Thread.sleep(new Random().nextInt(1000));
				this.data = data;
				System.out.println(Thread.currentThread().getName() + "我写的数据是：" + data);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				rwl.writeLock().unlock();  // 写释放锁
			}
		}

	}

}
