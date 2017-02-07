package com.wqj.observer._05;

public class WakeupEvent {

	//  是否饭点醒来
	private boolean isFoodTime;
	
	// 谁产生这个事件
	private Child baby;

	public WakeupEvent(boolean isFoodTime, Child baby) {
		super();
		this.isFoodTime = isFoodTime;
		this.baby = baby;
	}

	public boolean isFoodTime() {
		return isFoodTime;
	}

	public void setFoodTime(boolean isFoodTime) {
		this.isFoodTime = isFoodTime;
	}

	public Child getBaby() {
		return baby;
	}

	public void setBaby(Child baby) {
		this.baby = baby;
	}
	
}
