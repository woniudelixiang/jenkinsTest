package com.wqj.observer;

public class Dad implements Runnable{

	// 需要持有孩子的引用
	private Child child;
	
	public Dad() {
		super();
	}

	public Dad(Child child) {
		super();
		// 初始化孩子
		this.child = child;
	}


	// 给孩子喂食
	public void feed(Child child){
		System.out.println("给宝宝喂食.............");
	}
	

	@Override
	public void run() {
		try {
			//实时看孩子有没有醒来
			while(!this.child.isWakeup()){ // 孩子没有醒来
				for(int i = 0; i<5; i++){
					System.out.println("宝宝沉睡中国，无法喂食...."+ (i+1));
						// 睡觉5秒
						Thread.sleep(1000*1);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 孩子醒来了
		feed(child);
		
	}
	
}
