package com.wqj.common.util;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexUtil {
	
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public final static String BEGIN_TIME_STRING = " 00:00:00";
	public final static String FINAL_TIME_STRING = " 23:59:59";
	
	public static SimpleDateFormat getFormat(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format;
	}
	
	// "yyyy-MM-dd"
	public static boolean isDate(String date){
		final String regex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		final Pattern pattern = Pattern.compile(regex);  
		final Matcher mat = pattern.matcher(date);  
		if (mat.matches()) {  
			return true;  
		}  
		return false;  
	}
	
	// "yyyy-MM-dd HH:mm:ss"
	public static boolean isDateTime(String date){
		final String regex = "(((01[0-9]{2}|0[2-9][0-9]{2}|[1-9][0-9]{3})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|((01[0-9]{2}|0[2-9][0-9]{2}|[1-9][0-9]{3})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|((01[0-9]{2}|0[2-9][0-9]{2}|[1-9][0-9]{3})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((04|08|12|16|[2468][048]|[3579][26])00))-0?2-29)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d";
		final Pattern pattern = Pattern.compile(regex);  
		final Matcher mat = pattern.matcher(date);  
		if (mat.matches()) {  
			return true;  
		}  
		return false;  
	}

	// "yyyy-MM-dd" 或者    "yyyy-MM-dd HH:mm:ss"
	public static boolean isDateOrDateTime(String date){
		final String regex = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))($|\\s([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)";
		final Pattern pattern = Pattern.compile(regex);  
		final Matcher mat = pattern.matcher(date);  
		if (mat.matches()) {  
			return true;  
		}  
		return false;  
	}
}
