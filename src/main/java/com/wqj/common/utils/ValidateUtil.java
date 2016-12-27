package com.wqj.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 判断对象是否为空
 * 
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月14日 上午11:57:44
 */
public class ValidateUtil {

	/**
	 * 判断对象不为空=
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean isNotEmpty(Object object) {
		if (object == null) {
			return false;
		}
		Class<?> clazz = object.getClass();

		if (clazz == String.class) {
			return !"".equals(object.toString().trim());
		}

		if (clazz == Collection.class) {
			return !(((Collection) object).size() == 0);
		}

		if (clazz == Map.class) {
			return !((Map) object).isEmpty();
		}

		if (clazz.isArray()) {
			return isNotEmptyByArray(object);
		}
		return true;
	}

	/**
	 * 判断对象不为空=
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		return !isNotEmpty(object);
	}

	// =============================== helper =============================
	public static <T> boolean isNotEmptyByArray(T object) {
		Class<?> clazz = object.getClass();
		// 首先判断是否为基本数据类型
		if (clazz.isArray()) {
			if (clazz.equals(int[].class)) {
				return !(((int[]) object).length == 0);
			} else if (clazz.equals(short[].class)) {
				return !(((short[]) object).length == 0);
			} else if (clazz.equals(char[].class)) {
				return !(((char[]) object).length == 0);
			} else if (clazz.equals(float[].class)) {
				return !(((float[]) object).length == 0);
			} else if (clazz.equals(double[].class)) {
				return !(((double[]) object).length == 0);
			} else if (clazz.equals(long[].class)) {
				return !(((long[]) object).length == 0);
			} else if (clazz.equals(boolean[].class)) {
				return !(((boolean[]) object).length == 0);
			} else if (clazz.equals(byte[].class)) {
				return !(((byte[]) object).length == 0);
			} else if (clazz.equals(Object[].class)) {
				return !(((Object[]) object).length == 0);
			}
		}
		return false;
	}

}
