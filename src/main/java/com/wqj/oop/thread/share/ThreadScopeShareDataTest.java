package com.wqj.oop.thread.share;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeShareDataTest {

	public static int data = 0;
	public static Map<Thread,Integer> dataMap = new HashMap<Thread,Integer>();
	public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	public static ThreadLocal<ShareData> shareDataLocal = new ThreadLocal<ShareData>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
//					Integer randomData = new Random().nextInt(100);
//					System.out.println(Thread.currentThread().getName() +" ,  " + "产生的数据是：" + randomData);
					
//					data = randomData;
//					new AModule().sayA(data);
//					new BModule().sayB(data);
					
//					dataMap.put(Thread.currentThread(), randomData);
//					new AModule().sayA(dataMap);
//					new BModule().sayB(dataMap);
					
//					threadLocal.set(randomData);
//					new AModule().sayA(threadLocal);
//					new BModule().sayB(threadLocal);
					
					// 多个数据的处理
//					ShareData shareData = new ShareData();
//					shareData.setName(Thread.currentThread().getName());
//					shareData.setAge(1);
//					System.out.println(Thread.currentThread().getName() +" ,  " + "产生的数据是：" + shareData);
//					shareDataLocal.set(shareData);
//					new AModule().sayAL(shareDataLocal);
//					new BModule().sayBL(shareDataLocal);
					
					
					ShareDataPack threadInstance = ShareDataPack.getThreadInstance();  // new ShareDataPack();
					threadInstance.setName(Thread.currentThread().getName());
					threadInstance.setAge(1);
					System.out.println(Thread.currentThread().getName() +" ,  " + "产生的数据是：" + threadInstance);
					
					new AModule().sayA(threadInstance);
					new BModule().sayB(threadInstance);
					
					
				}
			}).start();
		}
		
		
	}
	
}
