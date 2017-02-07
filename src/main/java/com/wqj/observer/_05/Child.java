package com.wqj.observer._05;

import java.util.ArrayList;
import java.util.List;

public class Child implements Runnable{

	// 监听者集合
	private List<WakeupListener> listeners = new ArrayList<WakeupListener>();
	
	// 添加监听者
	public void addListener(WakeupListener wakeupListener){
		listeners.add(wakeupListener);
	}

	public void setWakeup() {
		for (WakeupListener wakeupListener : listeners) {
			wakeupListener.actionToWakeup(new WakeupEvent(true, this));
		}
	}

	@Override
	public void run() {
		try {
			boolean flag = true;
			while(flag){ 
				for(int i = 0; i<5; i++){
					System.out.println("宝宝沉睡中国，无法喂食...."+ (i+1));
						// 睡觉5秒
						Thread.sleep(1000*1);
				}
				flag = false;
				// 孩子醒来了
				setWakeup();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
