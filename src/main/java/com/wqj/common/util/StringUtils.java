package com.wqj.common.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Sets;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class StringUtils {

	public static final String COMMA = ",";
	public static final String SEMICOLON = "\\;";
	public static final String _COMMA = "\\,";
	public static final String EMPTY = " ";
	public static final String UNDERLINE = "_";
	public static final String SINGLE_QUOTE = "'";
	public static final String P_SINGLE_QUOTE = "\\\\'";
	public static final String FEMPTY = " ";

	/**
	 * 获得前一篇文章ID
	 * 
	 * @param ids
	 * @param curentId
	 * @return
	 */
	public static long getLastId(List<Long> ids, long curentId) {
		long lastId = 0;

		if (ids.size() > 0) {
			for (Long id : ids) {
				if (id < curentId) {
					lastId = id;
					break;
				}
			}
		}

		return lastId;
	}

	/**
	 * 获得后一篇文章ID
	 * 
	 * @param ids
	 * @param curentId
	 * @return
	 */
	public static long getNextId(List<Long> ids, long curentId) {
		long nextId = 0;

		if (ids.size() > 0) {
			for (Long id : ids) {
				if (id > curentId) {
					nextId = id;
					break;
				}
			}
		}

		return nextId;
	}

	/**
	 * 返回字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStr(Object obj) {
		return obj != null ? obj.toString() : "";
	}

	public static String replaceStr(String str, String target,
			String destination) {
		return str.replaceAll(target, destination);
	}
	
	/**
	 * 
	 * 
	 * 
	 */

	public static double getDouble(Object obj) {
		return obj != null && obj != "" ? Double.parseDouble(obj.toString())
				: 0.00;
	}

	/**
	 * 返回Integer
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer getInt(Object obj) {
		return obj != null ? Integer.valueOf(obj.toString()) : 0;
	}

	public static Integer getInteger(Object obj) {
		return obj != null ? Integer.valueOf(obj.toString()) : -99;
	}

	/**
	 * 返回Long
	 * 
	 * @param obj
	 * @return
	 */
	public static Long getLong(Object obj) {
		return obj != null ? Long.valueOf(obj.toString()) : 0;
	}

	/**
	 * 返回Long
	 * 
	 * @param obj
	 * @return
	 */
	public static Long getLongNull(Object obj) {
		return obj != null ? Long.valueOf(obj.toString()) : -99;
	}

	/**
	 * 返回BigDecimal
	 * 
	 * @param obj
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object obj) {
		return obj != null ? new BigDecimal(obj.toString()) : new BigDecimal("0");
	}

	/**
	 * 起始日期的时间
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStartDate(Object obj) {
		if (obj != null) {
			obj = obj + " 00:00:00";
			return obj.toString();
		} else {
			return "";
		}
	}

	/**
	 * 终止日期的时间
	 * 
	 * @param obj
	 * @return
	 */
	public static String getEndDate(Object obj) {
		if (obj != null) {
			obj = obj + " 23:59:59";
			return obj.toString();
		} else {
			return "";
		}
	}

	/**
	 * 返回日期的字符串
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getDateStr(Object obj) {
		java.sql.Timestamp dateTime = (java.sql.Timestamp) obj;
		String dateStr = "-";
		if (dateTime != null) {
			dateStr = StringUtils.getStr(dateTime.toLocaleString());
		}

		return dateStr;
	}

	/**
	 * 将Long型日期转化成字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getLong2DateStr(Object obj) {
		String dateStr = "-";
		long longDate = getLong(obj);
		if (longDate > 0) {
			dateStr = DateUtils.long2LongString(longDate);
		}

		return dateStr;
	}

	/**
	 * 判断是否为数字类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isNoEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}

		return false;
	}
	
	public static Set<Long> stringToSet(String str) {
		Set<Long> result = Sets.newHashSet();
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return result;
		}
		for (String value : str.split(COMMA)) {
			result.add(Long.valueOf(value));
		}
		return result;
	}
	
	public static String collectionToString(Collection<Object> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return "";
		} else {
			StringBuilder builder = new StringBuilder();
			for (Object obj : collection) {
				if (builder.length() != 0) {
					builder.append(COMMA).append(String.valueOf(obj));
				} else {
					builder.append(String.valueOf(obj));
				}
			}
			return builder.toString();
		}
	}
	
	/**
	 * 如果是手机号码，则隐藏手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static String hidePhoneNum(String mobiles) {
		if (isPhone(mobiles)) {
			return mobiles.substring(0, 7) + "****";
		}
		return mobiles;
	}
	
	/**
	 * 判断手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isPhone(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**
	 * 校验字符串是否是[1,7]的数字
	 * @param string
	 * @return
	 */
	public static boolean isNum(String string){
		Pattern pattern = Pattern.compile("[1-7]{1}\\d*");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}
	
	/**
	 * 去空格
	 * 
	 * @param str
	 * @return
	 */
	public static String strTrim(String str){
		return str.trim().replaceAll(EMPTY, "");
	}
}
