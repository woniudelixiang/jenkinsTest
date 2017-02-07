package com.wqj.observer._03;

public class GFather {
	
	@SuppressWarnings("unused")
	private WakeupEvent event;
	 
	public void actionToWakeup(WakeupEvent event){
		this.event = event;
		// 对事件进行分析，进行相应的处理
		if(event.isFoodTime()){
			feed();
		}else{
			play();
		}
	}
	
	// 给孩子喂食
	public void feed(){
		System.out.println("爷爷给宝宝喂食.............");
	}
	
	// 抱孩子出去玩
	public void play(){
		System.out.println("爷爷抱孩子出去玩！！！");
	}
	
}
