package com.wqj.common.utils;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.wqj.common.util.ReflectionUtils;

public class ConvertUtil {
	@SuppressWarnings("unused")
	private static String CLASS_NAME = ConvertUtil.class.getName();
	
	/**
	 * 将Map转换成Javabean(该map的key姓氏如：student.user.username)
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T convertMap(Map<String, Object> map, Class<T> clazz) throws Exception {
		T t = clazz.newInstance();
		for (String key : map.keySet()) {
			Object objValue = map.get(key);
			setValue(key, objValue, t);
		}
		return t;
	}
	
    /**
     * map转javaBean (该map的key形式如：student)
     * @param map
     * @param object
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>T convertMap(Map<String,Object> map, T object) throws Exception{
    	System.out.println("object:" + object.getClass().getName());
    	Field field = null;
    	for (String key : map.keySet()) {
			Object obj = map.get(key);
			
//			field = object.getClass().getDeclaredField(key);
			field = getField(object, key);
			if(field == null){
				continue;
			}
			
			field.setAccessible(true);
			
//			if(obj.getClass() == com.google.gson.internal.StringMap.class){
//				System.out.println("~~~~~~~~~~~~~~~~~~ field " + field.getName());
//				Object o = field.get(object); // 获取此类变量值 (直接获取跳过 get 方法)
//				if(o == null){
//					o = field.getType().newInstance();
//				}
//				obj = convertMap((Map)obj, o);
//			}
			
			if(obj instanceof AbstractMap){
				Object o = field.get(object); // 获取此类变量值 (直接获取跳过 get 方法)
				if(o == null){
					o = field.getType().newInstance();
				}
				obj = convertMap((Map)obj, o);
				
			}
			
			BeanUtils.setProperty(object, field.getName(), obj);
		}
    	return object;
    }
    
    public static Field getField(Object object , String key){
    	Field field = null;
    	List<Class<?>> clazzs = ReflectionUtils.getClasses(object);
    	for (Class<?> clazz : clazzs) {
    		try {
				return clazz.getDeclaredField(key);
			} catch (Exception e) {
				continue;
			} 
		}
    	return field;
    }
    
    
    
    
    
    
	//=============================================== helper =================================================
	/**
	 * 属性赋值(支持递归)
	 * @param key     属性值
	 * @param value   值
	 * @param obj     对象
	 * @return
	 * @throws Exception
	 */
	public static Object setValue(String key, Object value, Object obj) throws Exception {
		Field field = null;
		Class<?> clazz = obj.getClass();
		int index = key.indexOf(".");
		if (index > 0) {
			String sub = key.substring(0, index);
			field = clazz.getDeclaredField(sub);
			field.setAccessible(true);
			Object object = field.get(obj); // 获取此类变量值 (直接获取跳过 get 方法)
			if(object == null){
				object = field.getType().newInstance();
			}
			key = key.substring(index + 1);
			Object o = setValue(key, value, object);
			field.setAccessible(true);
			BeanUtils.setProperty(obj, field.getName(), o);
		} else {
			field = clazz.getDeclaredField(key);
			field.setAccessible(true);
			BeanUtils.setProperty(obj, field.getName(), value);
		}
		return obj;
	}
}
