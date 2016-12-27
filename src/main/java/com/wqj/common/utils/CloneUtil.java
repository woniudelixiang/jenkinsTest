package com.wqj.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import com.wqj.common.factory.BeanFactory;
// http://www.zuidaima.com/code/file/1086071548462080.htm?dir=/1086071548462080.java

/**
 * 对象的克隆
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月14日 上午10:30:14
 */
public class CloneUtil {

	/**
	 * 浅表复制对象
	 * 
	 * @param value
	 *            原始对象
	 * @return 复制后的对象，只复制一层
	 * @throws Exception
	 */
	public static <T> T clone(T value) throws Exception {
		return clone(value, 1);
	}

	/**
	 * 深度复制对象
	 * 
	 * @param value
	 *            原始对象
	 * @return 复制后的对象
	 * @throws Exception
	 */
	public static <T> T deepClone(T value) throws Exception {
		return clone(value, -1);
	}

	// ==================================== helper ============================================
	/**
	 * 无需进行复制的特殊类型数组
	 */
	@SuppressWarnings("rawtypes")
	static Class[] needlessCloneClasses = new Class[] { String.class, Boolean.class, Character.class, Byte.class,
			Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class, Object.class, Class.class };

	/**
	 * 判断该类型对象是否无需复制
	 * 
	 * @param c
	 *            指定类型
	 * @return 如果不需要复制则返回真，否则返回假
	 */
	@SuppressWarnings("rawtypes")
	private static boolean isNeedlessClone(Class c) {
		if (c.isPrimitive()) {// 基本类型
			return true;
		}

		for (Class tmp : needlessCloneClasses) { // 是否在无需复制类型数组里
			if (c.equals(tmp)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 复制对象数据
	 * 
	 * @param value
	 *            原始对象
	 * @param level
	 *            复制深度。小于0为无限深度，即将深入到最基本类型和Object类级别的数据复制；
	 *            大于0则按照其值复制到指定深度的数据，等于0则直接返回对象本身而不进行任何复制行为。
	 * @return 返回复制后的对象
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T clone(T value, int level) throws Exception {
		if (value == null) {
			return null;
		}
		if (level == 0) {
			return value;
		}
		Class c = value.getClass();
		if (isNeedlessClone(c)) {
			return value;
		}
		level--;

		if (value instanceof Collection) { // 复制新的集合
			Collection tmp = BeanFactory.newInstance(c);
			for (Object v : (Collection) value) {
				tmp.add(clone(v, level));// 深度复制
			}
			value = (T) tmp;
		} else if (c.isArray()) { // 复制新的Array
			// 首先判断是否为基本数据类型
			if (c.equals(int[].class)) {
				int[] old = (int[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else if (c.equals(short[].class)) {
				short[] old = (short[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else if (c.equals(char[].class)) {
				char[] old = (char[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else if (c.equals(float[].class)) {
				float[] old = (float[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else if (c.equals(double[].class)) {
				double[] old = (double[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else if (c.equals(long[].class)) {
				long[] old = (long[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else if (c.equals(boolean[].class)) {
				boolean[] old = (boolean[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else if (c.equals(byte[].class)) {
				byte[] old = (byte[]) value;
				value = (T) Arrays.copyOf(old, old.length);
			} else {
				Object[] old = (Object[]) value;
				Object[] tmp = (Object[]) Arrays.copyOf(old, old.length, old.getClass());
				for (int i = 0; i < old.length; i++) {
					tmp[i] = clone(old[i], level);
				}
				value = (T) tmp;
			}
		} else if (value instanceof Map) { // 复制新的MAP
			Map tmp = BeanFactory.newInstance(c);
			Map org = (Map) value;
			for (Object key : org.keySet()) {
				tmp.put(key, clone(org.get(key), level)); // 深度复制
			}
			value = (T) tmp;
		} else {
			Object tmp = BeanFactory.newInstance(c);
			if (tmp == null) { // 无法创建新实例则返回对象本身，没有克隆
				return value;
			}

			Set<Field> fields = BeanUtil.getPropertyDescriptorsToSet(c);

			for (Field field : fields) {
				if (!Modifier.isFinal(field.getModifiers())) {// 仅复制非final字段
					field.setAccessible(true);
					field.set(tmp, clone(field.get(value), level));// 深度复制
				}
			}
			value = (T) tmp;
		}
		return value;
	}
}
