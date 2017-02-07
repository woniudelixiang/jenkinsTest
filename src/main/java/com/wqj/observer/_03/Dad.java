package com.wqj.observer._03;

public class Dad extends GFather{

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
}
