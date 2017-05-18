package com.wqj.java5.enumeration;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

// 其使用方式和普通的Set没有区别，只是构造方法有一些特殊的而已
public class EnumSetTest {

	public static void main(String[] args) {
		// 创建一个指定类型的空的集合
		EnumSet<WeekDay3> set = EnumSet.noneOf(WeekDay3.class);
		set.add(WeekDay3.MONDAY);
		set.add(WeekDay3.TUESDAY);
		set.add(WeekDay3.WENSDAY);
		showSet(set);

		// 创建指定类型的所有数据的集合
		EnumSet<WeekDay3> set2 = EnumSet.allOf(WeekDay3.class);
		showSet(set2);

		// 创建指定类型指定初始数据的集合
		EnumSet<WeekDay3> set3 = EnumSet.of(WeekDay3.MONDAY, WeekDay3.TUESDAY, WeekDay3.WENSDAY);
		showSet(set3);

		// 创建指定类型，指定范围的集合 (包含边界数据)
		EnumSet<WeekDay3> set4 = EnumSet.range(WeekDay3.MONDAY, WeekDay3.THURSDAY);
		showSet(set4);
	}

	// 显示Set里面的数据
	private static void showSet(Set<?> set) {
		System.out.println(Arrays.toString(set.toArray()));
	}
}