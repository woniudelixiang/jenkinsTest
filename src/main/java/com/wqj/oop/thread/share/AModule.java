package com.wqj.oop.thread.share;

import java.util.Map;

public class AModule {

	public void sayA(int i){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + i);
	}
	
	public void sayA(Map<Thread,Integer> dataMap){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + dataMap.get(Thread.currentThread()));
	}
	public void sayA(ThreadLocal<Integer> threadLocal){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + threadLocal.get());
	}
	
	public void sayAL(ThreadLocal<ShareData> shareDataLocal){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + shareDataLocal.get());
	}
	public void sayA(ShareDataPack shareDataPack){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + shareDataPack.toString());
	}
}
