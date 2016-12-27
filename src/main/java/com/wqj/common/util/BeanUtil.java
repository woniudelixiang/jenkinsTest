package com.wqj.common.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class BeanUtil {

	/**
	 * 获取类属性 (包括父类)  
	 * @param clazz
	 * @return
	 */
	public static Map<String,Field> getPropertyDescriptors(Class<?> clazz){
		Map<String,Field> clazzMaps = Maps.newHashMap();
		List<Class<?>> clazzs = getClasses(clazz);
		for(Class<?> c : clazzs){
			Field[] fields =c.getDeclaredFields();
			Field.setAccessible(fields, true);
			for(Field field : fields){
				clazzMaps.put(field.getName(), field);
			}
		}
		return clazzMaps;
	}

	/**
	 * 获取超类信息（不包括Object）
	 * @param clazz
	 * @return
	 */
	public static List<Class<?>> getClasses(Class<?> clazz){
		List<Class<?>> clazzs = Lists.newArrayList();
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			clazzs.add(superClass);
		}
		return clazzs;
	}
	
	
}
