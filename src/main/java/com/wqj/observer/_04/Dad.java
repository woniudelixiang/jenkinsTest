package com.wqj.observer._04;

public class Dad implements WakeupListener{

	@SuppressWarnings("unused")
	private WakeupEvent event;
	
	public Dad() {
		super();
	}

	// 给孩子喂食
	public void feed(){
		System.out.println("爸爸给宝宝喂食.............");
	}
	
	// 抱孩子出去玩
	public void play(){
		System.out.println("爸爸抱孩子出去玩！！！");
	}
	
	public void actionToWakeup(WakeupEvent event){
		this.event = event;
		// 对事件进行分析，进行相应的处理
		if(event.isFoodTime()){
			feed();
		}else{
			play();
		}
	}
}
