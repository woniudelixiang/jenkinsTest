package com.wqj.java5.enumeration;

public enum WeekDay3 {
	// 星期一
	MONDAY(1),
	// 星期二
	TUESDAY(2),
	// 星期三
	WENSDAY(3),
	// 星期四
	THURSDAY(4),
	// 星期五
	FRIDAY(5);

	private int value;

	// 构造方法私有化
	private WeekDay3(int i) {
		this.value = i;
	}

	public int getValue() {
		return value;
	}
}
