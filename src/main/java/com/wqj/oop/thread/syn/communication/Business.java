package com.wqj.oop.thread.syn.communication;

/**
 * 主要演示的是  线程同步和线程间的通信
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年2月23日 上午10:32:33
 */
public class Business {

	// 子线程是否可以做
	boolean isShouldSub = true;
	
	// 子线程做的业务
	public synchronized void sub(int j){
		while(!isShouldSub){
			try {
				this.wait();   // 子线程等待
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println("子线程，第 " + (j+1) + " 轮，第 " + (i+1) +" 次打印");
		}
		isShouldSub = false;
		this.notify();  // 唤醒主线程
	}
	
	// 主线程做的业务
	public synchronized void main(int j){
		while(isShouldSub){
			try {
				this.wait();  // 主线程等待
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
		for (int i = 0; i < 20; i++) {
			System.out.println("========================>>>>  主线程，第 " + (j+1) + " 轮，第 " + (i+1) +" 次打印");
		}
		isShouldSub = true;
		this.notify();  // 唤醒子线程
	}
}
