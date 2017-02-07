package com.wqj.observer;

public class Child implements Runnable{

	// 是否醒来
	private boolean wakeup = false;

	public boolean isWakeup() {
		return wakeup;
	}

	public void setWakeup(boolean wakeup) {
		this.wakeup = wakeup;
	}

	@Override
	public void run() {
		try {
			// 睡觉5秒
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 小孩子醒来
		setWakeup(true);
	}
	
}
