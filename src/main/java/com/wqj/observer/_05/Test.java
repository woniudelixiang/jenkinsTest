package com.wqj.observer._05;

public class Test {
	
	public static void main(String[] args) {
		Dad dad = new Dad();
		GFather gf = new GFather();
		Child baby = new Child();
		// 添加监听者
		baby.addListener(dad);
		baby.addListener(gf);
		
		new Thread(baby).start();
	}
	
}
