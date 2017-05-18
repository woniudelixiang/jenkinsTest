package com.wqj.java5.enumeration;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapTest {
	public static void main(String[] args) {
		EnumMap<WeekDay3, String> errMsgMap = new EnumMap<WeekDay3, String>(WeekDay3.class);  
		errMsgMap.put(WeekDay3.MONDAY, "我代表星期一");
		errMsgMap.put(WeekDay3.WENSDAY, "我代表星期二");
		for (Map.Entry<WeekDay3, String> entry : errMsgMap.entrySet()) {
			System.out.println(entry.getKey().name() + " : " + entry.getValue());  
		}
	}
}
