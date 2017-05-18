package com.wqj.java5.genericity;

import java.util.ArrayList;
import java.util.Date;

public class Test1 {
	public static void main(String[] args) {
		ArrayList<Date> list = new ArrayList<Date>();
		list.add(new Date());
		Date myDate = list.get(0);
		
		Object object = new Object();
	}
}