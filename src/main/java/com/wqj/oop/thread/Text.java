package com.wqj.oop.thread;

public class Text {

	/**
	 * 视频学习教程： 
	 * 	  【传智播客张孝祥】Java多线程与并发库高级应用视频教程：   http://video.1kejian.com/itjishu/java/78862/
	 *   
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		// 每一次执行串接操作都会导致新对象的产生
		String result1 = test("1","");
		String result2 = test("1","");
//		String result1 = "1"+"";
//		String result2 = "1"+"";
		System.out.println(result1 == result2);
		System.out.println(result1.intern() == result2.intern());
	}
	
	
	public static String test(String key1 , String key2){
		return key1 + key2;
	}
	
}
