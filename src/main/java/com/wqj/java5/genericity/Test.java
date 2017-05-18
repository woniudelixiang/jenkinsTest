package com.wqj.java5.genericity;

import java.util.ArrayList;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		// ArrayList<Date> list = new ArrayList<Date>();
		// list.add(new Date());
		// Date myDate = list.get(0);

		ArrayList list = new ArrayList();
		list.add(1);
		list.add(new Date());
		Date object = (Date) list.get(0);
		System.out.println(object);
	}

	// 返回二个对象中最大的
//	public static <T> T getMax1(T t1, T t2) {
//		if (t1.compareTo(t2) >= 0) { // 编译错误
//			return t1;
//		}
//		return t2;
//	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	// 返回二个对象中最大的
	public static <T extends Comparable> T getMax(T t1, T t2) {
		if (t1.compareTo(t2) >= 0) {
			return t1;
		}
		return t2;
	}

}