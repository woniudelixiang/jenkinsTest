package com.wqj.java5.enumeration;

public class WeekDay2 {
	// 星期一
	public static final WeekDay2 MONDAY = new WeekDay2(1);
	// 星期二
	public static final WeekDay2 TUESDAY = new WeekDay2(2);
	// 星期三
	public static final WeekDay2 WENSDAY = new WeekDay2(3);
	// 星期四
	public static final WeekDay2 THURSDAY = new WeekDay2(4);
	// 星期五
	public static final WeekDay2 FRIDAY = new WeekDay2(5);
	
	private int value;
	
	// 构造方法私有化
	private WeekDay2(int i) {
		this.value = i;
	}
	
	public int getValue() {
		return value;
	}
}
