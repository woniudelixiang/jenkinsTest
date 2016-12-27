package com.wqj.common.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jersey.repackaged.com.google.common.collect.Sets;

public class BeanUtil {
	@SuppressWarnings("unused")
	private static String CLASS_NAME = BeanUtil.class.getName();

	/**
	 * 获取类属性 (包括父类)  
	 * @param clazz
	 * @return
	 */
	public static Map<String,Field> getPropertyDescriptorsToMap(Class<?> clazz){
		Map<String,Field> map = Maps.newHashMap();
		List<Class<?>> clazzs = getClasses(clazz);
		for(Class<?> c : clazzs){
			Field[] fields = c.getDeclaredFields();
			Field.setAccessible(fields, true);
			for(Field field : fields){
				map.put(field.getName(), field);
			}
		}
		return map;
	}
	
	/**
	 * 获取类属性 (包括父类)
	 * @param clazz
	 * @return
	 */
	public static Set<Field> getPropertyDescriptorsToSet(Class<?> clazz){
		Set<Field> fieldSet = Sets.newHashSet();
		List<Class<?>> clazzs = getClasses(clazz);
		for(Class<?> c : clazzs){
			Field[] fields = c.getDeclaredFields();
			Field.setAccessible(fields, true);
			fieldSet.addAll(Arrays.asList(fields)); 
		}
		return fieldSet;
	}
	
	/**
	 * 获取所有父类（不包括Object）
	 * @param clazz
	 * @return
	 */
	public static List<Class<?>> getClasses(Class<?> clazz){
		// 接口的superClass为null
		List<Class<?>> clazzs = Lists.newArrayList();
		for (Class<?> superClass = clazz; (superClass != Object.class && superClass != null); superClass = superClass.getSuperclass()) {
			clazzs.add(superClass);
		}
		return clazzs;
	}
	
	/**
	 * 判断二个对象的内容是否相等
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean equals(Object o1,Object o2){
		if(ValidateUtil.isEmpty(o1) || ValidateUtil.isEmpty(o2)){
			return false;
		}
		
		if(o1.equals(o2)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断二个对象的内容是否相等
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean notEquals(Object o1,Object o2){
		return !equals(o1, o2);
	}

	
	
	
	
	
}
