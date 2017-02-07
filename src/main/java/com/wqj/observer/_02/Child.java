package com.wqj.observer._02;

public class Child implements Runnable{

	// 持有父亲引用
	private Dad dad;
	
	// 是否醒来
	private boolean wakeup = false;
	
	public Child() {
		super();
	}

	public Child(Dad dad) {
		super();
		this.dad = dad;
	}

	public boolean isWakeup() {
		return wakeup;
	}

	public void setWakeup(boolean wakeup) {
		this.wakeup = wakeup;
		dad.feed(this);
	}

	@Override
	public void run() {
		try {
			while(!this.isWakeup()){ // 孩子没有醒来
				for(int i = 0; i<5; i++){
					System.out.println("宝宝沉睡中国，无法喂食...."+ (i+1));
						// 睡觉5秒
						Thread.sleep(1000*1);
				}
				// 孩子醒来了
				setWakeup(true);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
