package com.wqj.java5.enumeration;

public class SwitchTest {

	public static void printWeekDay(WeekDay3 weekDay) {
		switch (weekDay) {
		case MONDAY:
			System.out.println("Today is Monday!");
			break;
		case TUESDAY:
			System.out.println("Today is Tuesday!");
			break;
		case WENSDAY:
			System.out.println("Today is Wensday!");
			break;
		case THURSDAY:
			System.out.println("Today is hursday!");
			break;
		case FRIDAY:
			System.out.println("Today is Friday!");
			break;
		default:
			throw new AssertionError("Unexpected value: " + weekDay);
		}
	}
	
	public static void main(String[] args) {
		printWeekDay(WeekDay3.FRIDAY);
	}
}
