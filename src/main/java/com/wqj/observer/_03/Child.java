package com.wqj.observer._03;

public class Child implements Runnable{

	// 持有父亲引用
	private Dad dad;
	
	public Child() {
		super();
	}

	public Child(Dad dad) {
		super();
		this.dad = dad;
	}

	public void setWakeup() {
		dad.actionToWakeup(new WakeupEvent(true, this));
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
