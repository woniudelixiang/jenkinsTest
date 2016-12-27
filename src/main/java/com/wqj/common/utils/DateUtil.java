package com.wqj.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class DateUtil {

	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATE_NO = "yyyyMMdd";
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_TIME = "HH:mm";
	public static final String PATTERN_NO_YEAR = "MM-dd HH:mm";
	public final static String START_TIME_STRING = " 00:00:00";
	public final static String FINAL_TIME_STRING = " 23:59:59";

	/**
	 * 时间前推或后推分钟,其中minute表示分钟.
	 * 
	 * @param date
	 *            必须是yyyy-MM-dd HH:mm:ss格式
	 * @param minute
	 *            正数表示后退，负数表示前退
	 * 
	 */
	public static String getPreTime(String date, String minute) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(date);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(minute)
					* 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 判断给定的时间的minute时间是否在当前系统时间的之前
	 * 
	 * @param date
	 * @param minute
	 * @return 如果是在指定的minute之前【包含连接点时间】返回true，否则返回false【如果date为空也返回false】
	 */
	public static boolean getIsPreTime(String date, String minute) {
		Date dt = new Date();
		String strDate = datetime2String(dt);// 当前系统时间的字符串形式
		String preTime = getPreTime(date, minute);
		/*
		 * Str1.compareTo(Str2); 其返回的是一个int类型值。 若Str1等于参数字符串Str2字符串，则返回0；
		 * 若该Str1按字典顺序小于参数字符串Str2，则返回值小于0； 若Str1按字典顺序大于参数字符串Str2，则返回值大于0。
		 */
		if (com.wqj.common.util.StringUtils.isNoEmpty(date)) {
			if (preTime.compareTo(strDate) < 0) {
				return true;
			} else if (preTime.compareTo(strDate) >= 0) {
				return false;
			}
		}
		return false;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static long strSLong(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return 0l;
		}
		Date date = string2Datetime(dateTime);

		return date.getTime();
	}

	/**
	 * 判断当前系统时间是否在给定的二个时间之间
	 * 
	 * @param dateStr1
	 *            【格式是：yyyy-MM-dd HH:mm:ss】
	 * @param dateStr2
	 * @return
	 */
	public static boolean getIsBetween(String dateStr1, String dateStr2) {
		if (com.wqj.common.util.StringUtils.isNoEmpty(dateStr1)
				&& com.wqj.common.util.StringUtils.isNoEmpty(dateStr2)) {
			Date date1 = string2Datetime(dateStr1);
			Date date2 = string2Datetime(dateStr2);
			Date nowDate = new Date();
			if (nowDate.after(date1) && nowDate.before(date2)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 判断当前系统时间是否在给定的二个时间之间
	 * 
	 * @param dateStr1
	 *            【格式是：yyyy-MM-dd HH:mm:ss】
	 * @param dateStr2
	 * @return
	 */
	public static boolean getIsPre(String dateStr1) {
		if (com.wqj.common.util.StringUtils.isNoEmpty(dateStr1)) {
			Date date1 = string2Datetime(dateStr1);
			Date nowDate = new Date();
			if (nowDate.before(date1)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static DateTime toDateTime(String dateTime) {
		if (!com.wqj.common.util.StringUtils.isNoEmpty(dateTime)) {
			return null;
		}
		return DateTimeFormat.forPattern(PATTERN_DATETIME).parseDateTime(
				dateTime);
	}

	public static String dateTimetoStringDATETIME(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATETIME);
	}
	
	public static String dateTimetoStringDATE(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATE);
	}
	
	public static String getStartDateTimeStr(DateTime dateTime){
		int year = dateTime.getYear();
		int monthOfYear = dateTime.getMonthOfYear();
		int dayOfMonth = dateTime.getDayOfMonth();
		dateTime = new DateTime(year, monthOfYear, dayOfMonth, 0, 0, 0);
		return dateTime.toString(PATTERN_DATETIME);
	}
	
	public static DateTime getStartDateTime(DateTime dateTime){
		int year = dateTime.getYear();
		int monthOfYear = dateTime.getMonthOfYear();
		int dayOfMonth = dateTime.getDayOfMonth();
		dateTime = new DateTime(year, monthOfYear, dayOfMonth, 0, 0, 0);
		return dateTime;
	}
	
	public static String getFinalDateTimeStr(DateTime dateTime){
		int year = dateTime.getYear();
		int monthOfYear = dateTime.getMonthOfYear();
		int dayOfMonth = dateTime.getDayOfMonth();
		dateTime = new DateTime(year, monthOfYear, dayOfMonth, 23, 59, 59);
		return dateTime.toString(PATTERN_DATETIME);
	}
	
	public static DateTime getFinalDateTime(DateTime dateTime){
		int year = dateTime.getYear();
		int monthOfYear = dateTime.getMonthOfYear();
		int dayOfMonth = dateTime.getDayOfMonth();
		dateTime = new DateTime(year, monthOfYear, dayOfMonth, 23, 59, 59);
		return dateTime;
	}
	
	
	public static String dateTimetoString(DateTime dateTime, String pattern) {
		return dateTime.toString(PATTERN_DATE);
	}

	public static String toString(String dateTime, String pattern) {
		return new DateTime(dateTime).toString(pattern);
	}
	
	public static String toString(LocalDate localDate, String pattern) {
		if (localDate == null) {
			return null;
		}
		return localDate.toString(pattern);
	}

	public static String today2String() {
		return new LocalDate().toString(PATTERN_DATE);
	}

	public static String today3String() {
		return new LocalDate().toString(PATTERN_DATE_NO);
	}

	public static String getCurrentTime() {
		return new LocalDate().toString(PATTERN_DATETIME);
	}

	// Date类型转换为字符串 && 将字符串转换为Date类型
	public static String datetime2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(date);
	}

	// Date类型转换为字符串 && 将字符串转换为Date类型
	public static String datetimeString(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String date2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		return sdf.format(date);
	}

	public static String date3String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		return sdf.format(date);
	}

	public static String date4String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(date);
	}
	
	public static String time2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_TIME);
		return sdf.format(date);
	}

	public static String time3String(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将字符串时间转换成Date类型
	 * 
	 * @param date
	 *            【形式如：2015-05-25 11:15:00】
	 * @return
	 */
	public static Date string2Datetime(String date) {
		Date retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		try {
			retValue = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	public static Date string2Date(String date) {
		Date retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try {
			retValue = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return返回字符串格式 yyyy-MM-dd
	 */
	public static String long2ShortString(long date) {
		if (date <= 0) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return返回字符串格式 MM月dd日 HH:ss
	 */
	public static String long3ShortString(long date) {
		if (date <= 0) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_NO_YEAR);
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String long2LongString(long date) {
		if (date == -99) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return返回字符串格式 format
	 */
	public static String long3LongString(long date, String format) {
		if (date == -99) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(date));
	}

	/**
	 * 获取指定日期一年前时间
	 * 
	 * @return返回long格式
	 */
	public static long getChainYear(long date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.YEAR, -1);
		return cal.getTime().getTime();
	}

	/**
	 * 北京时间转格林威治时间
	 * 
	 * @return返回long格式
	 */
	public static long stringBj2GmtLong(String date) {
		Date retValue = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		try {
			retValue = sdf.parse(date);
			cal.setTime(retValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.getTime().getTime();
	}

	
	public static long strToLongStart(DateTime dateTime) {
		Date date = null;
		
		Calendar cal = Calendar.getInstance();
		String dateTimeStr = getStartDateTimeStr(dateTime);
		System.out.println(dateTimeStr);
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		try {
			 date = sdf.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		return cal.getTime().getTime();
	}
	
	public static long strToLongEnd(DateTime dateTime) {
		Date date = null;
		
		Calendar cal = Calendar.getInstance();
		String dateTimeStr =  getFinalDateTimeStr(dateTime);
		System.out.println(dateTimeStr);
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		try {
			 date = sdf.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		return cal.getTime().getTime();
	}
	
	/**
	 * 根据时区转格林威治时间
	 * 
	 * @param date
	 *            需要转换的原时间
	 * @param timeZone
	 *            时区转换数字
	 * @return 返回long格式
	 * @author yuting gao
	 */
	public static long stringTime2GmtLong(String date, Double timeZone) {
		Date retValue = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		try {
			retValue = sdf.parse(date);
			cal.setTime(retValue);
			if (timeZone % 1 == 0.5) {
				if (timeZone > 0) {
					cal.add(Calendar.MINUTE, +30);
				} else if (timeZone < 0) {
					cal.add(Calendar.MINUTE, -30);
				}
			}
			cal.add(Calendar.HOUR, timeZone.intValue() + 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.getTime().getTime();
	}

	public static String todayToString() {
		return new LocalDate().toString("yyyyMMdd");
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */

	public static String long2DateString(Long date, String pattern) {
		if (date == null || date.longValue() == 0) {
			return "-";
		}
		if (date.longValue() == -99) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型格林威治时间格式转换为北京时间字符格式
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String longgmt2strbj(Long date) {
		Date nowTime = new Date(date); // 要转换的时间
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(nowTime.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_TIME);
		return sdf.format(cal.getTime());
	}

	/** 获取测试的年月日字串 **/
	/*
	 * public static String getTestDateShortString(){ return "2013-3-1"; }
	 */

	/***
	 * 时间差，分钟
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String timeDifference(long startTime, long endTime) {
		long timeDifferenceSecond = endTime - startTime;
		double dmin = ((double) timeDifferenceSecond / (60 * 1000));
		BigDecimal bd = BigDecimal.valueOf(dmin);
		DecimalFormat df = new DecimalFormat("0");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(bd);
	}

	/**
	 * 凌晨
	 * 
	 * @param date
	 * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
	 *       1 返回yyyy-MM-dd 的二天00:00:00日期
	 * @return
	 */
	public static Date weeHours(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second
				* 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

		if (flag == 0) {
			return cal.getTime();
		} else if (flag == 1) {
			// 凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis() + 24 * 60 * 60 * 1000);
		}
		return cal.getTime();
	}

	// 相差一个月时间
	public static long getpreMonth() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); // 年
		int month = cal.get(Calendar.MONTH);// 月
		int day = cal.get(Calendar.DATE); // 日
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		String date = year + "-" + month + "-" + day + " " + hour + ":"
				+ minute + ":" + second;
		return strSLong(date);
	}

	// 得到当前月前N个月是所在年的几月
	public static String getMonthByParam(int param) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, -param);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		return year + "/" + month;
	}
	
	// 得到当前月前N个月是所在年的几月
	public static int getLastMonthByParam(int param) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, -param);
		int month = ca.get(Calendar.MONTH) + 1;
		return month;
	}
	
	//param=1 上月月初
	public static Date getMonthDateByParam(int param) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -param);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	//param=1 上月月底	
	public static Date getTimesMonthNight(int param) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -param);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
	}
	
	// 得到当前周前N个周是所在年的第几周
	public static String getWeekByParam(int param) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.WEEK_OF_YEAR, -param);
		int week = ca.get(Calendar.WEEK_OF_YEAR);
		int year = ca.get(Calendar.YEAR);
		if (week == 1) {
			year += 1;
		}
		return year + "第" + week + "周";
	}

	// 得到当前周前N个周是所在年的第几周
	public static Date getWeekDateByParam(int param, int week) {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		cal.add(Calendar.DATE, param * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	// 当前天前N天
	public static Date getDayTimeByParam(int param) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.DATE, -param);
		Date time = ca.getTime();
		return time;
	}

	/*public static void main(String[] args) {
		Date date = DateUtils.getDayTimeByParam(0);
		String dateTime = DateUtils.date2String(date);
		String startDate = dateTime+Const.ZERO_TIME_STRING;
		String endDate = dateTime+Const.FINAL_TIME_STRING;
		System.out.println(startDate+" , "+endDate);
	}*/
	
	/*public static void main(String[] args) {
		getDayTimeByParam(7);
	}*/
	
}
