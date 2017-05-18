package com.wqj.oop.thread.share;

import java.util.Map;

public class BModule {

	public void sayB(int i){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + i);
	}
	
	public void sayB(Map<Thread,Integer> dataMap){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + dataMap.get(Thread.currentThread()));
	}
	
	public void sayB(ThreadLocal<Integer> threadLocal){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + threadLocal.get());
	}
	
	public void sayBL(ThreadLocal<ShareData> shareDataLocal){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + shareDataLocal.get());
	}
	
	public void sayB(ShareDataPack shareDataPack){
		System.out.println(Thread.currentThread().getName() +" ,  " + "得到：" + shareDataPack.toString());
	}
	
}
