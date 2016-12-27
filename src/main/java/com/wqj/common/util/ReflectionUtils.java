package com.wqj.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wqj.daoDemo.entity.User;

public class ReflectionUtils {
	private static String CLASS_NAME = ReflectionUtils.class.getName();

	/**
	 * 将Map转换成Javabean
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T convertMap(Map<String, Object> map, Class<T> clazz) throws Exception {
		// 获取类中的所有属性（包括父类）
		Map<String, Field> propertyDescriptors = BeanUtil.getPropertyDescriptors(clazz);
		// 创建 JavaBean 对象
		T t = clazz.newInstance();

		for (String key : map.keySet()) {
			Field field = propertyDescriptors.get(key);
			Object objValue = map.get(key); // sql字段值
			int index = key.indexOf(".");

			if (index >= 0) {
				String sub = key.substring(0, index);
				field = propertyDescriptors.get(sub);
				System.out.println("name : " + field.getName());

				Object obj = field.get(t); // 获取此类变量值 (直接获取跳过 get 方法)

				key = key.substring(index + 1);
				objValue = keySplit(key, objValue, field.getType(), obj);
				System.out.println("objval: " + objValue);
			}

			Object objvalue = valueManage(objValue, field.getType());
			BeanUtils.setProperty(t, field.getName(), objvalue); // 给T中 类变量赋值
		}
		return t;
	}
	
	public static <T> T keySplit(String key, Object objValue, Class<T> clazz, Object obj) throws Exception {
		Field field = null;
		T t = (T) obj;
		if (ValidateUtils.isEmpty(t)) {
			t = clazz.newInstance();
		}

		int index = key.indexOf(".");
		if (index >= 0) {
			String sub = key.substring(0, index);
			field = clazz.getDeclaredField(sub);

			field.setAccessible(true);
			Object object = field.get(t); // 获取此类变量值 (直接获取跳过 get 方法)

			key = key.substring(index + 1);
			Object keySplit = keySplit(key, objValue, field.getType(), object);
			BeanUtils.setProperty(t, field.getName(), keySplit);
			System.out.println("keySplit : " + keySplit);
		} else {
			field = clazz.getDeclaredField(key);
			Object value = valueManage(objValue, field.getType());
			BeanUtils.setProperty(t, field.getName(), value);
		}
		return t;
	}
	
	/**
	 * @author LE CHEN
	 * @param value
	 * @param typeClass
	 * @return
	 * 
	 * 		The type conversion!
	 */
	public static Object valueManage(Object value, Class<?> typeClass) {
//		ConvertUtilsBean convertUtils = new ConvertUtilsBean();
//		value = convertUtils.convert(value, typeClass);
		
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class arg0, Object arg1) {
				System.out.println("---->>>>>  "+arg0.getName() + "  arg1: " + arg1);
				// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.PATTERN_DATETIME);
				return DateUtils.toDateTime(sdf.format(arg1));
			}

		}, DateTime.class);

		
		return value;
	}
	
	public static void main(String[] args) throws Exception {
		Object value  = "123";
//		Timestamp value = new Timestamp(System.currentTimeMillis());
		Class<?> typeClass = Integer.class;
		
		Object valueManage = valueManage(value, typeClass);
		//A a = new ReflectionUtils().new A();
		User a = new User();
//		Field declaredField = a.getClass().getDeclaredField("value");
//		declaredField.setAccessible(true);
//		BeanUtils.setProperty(a, "createDateTime", value);
		System.out.println(valueManage);
		
	}
	
	
	class A {
		private DateTime value;

		public DateTime getValue() {
			return value;
		}

		public void setValue(DateTime value) {
			this.value = value;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(CLASS_NAME, "getFieldValue[{}]", e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
		Field field = getAccessibleField(obj, fieldName);
		
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(CLASS_NAME, "setFieldValue[{}]", e.getMessage());
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getAccessibleField(final Object object, final String fieldName) {
		Assert.notNull(object, "'object' must not be empty.");
		Assert.hasText(fieldName, "fieldName");

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}

	
public static  <T> T fillClassForMap(Map<String,Object> map,Class<T> clazz) {
		
		T t = null;
		
		try {
			t = clazz.newInstance();
			Map<String, Field> fieldMap = getAllFieldsMap(t);
			
			for(String key : map.keySet()){
				
				Field field = fieldMap.get(key);
				Object objValue = map.get(key); // sql字段值
				
				if(key.indexOf(".") >= 0){ //包含类 执行
					String[] clazzAndField = key.split("\\.");
					String clazzFieldName = clazzAndField[0]; //类中包含的 类变量名称
					field = fieldMap.get(clazzFieldName);
					if(ValidateUtils.isNotEmpty(field)){
						Object obj = field.get(t); //获取此类变量值 (直接获取跳过 get 方法)
						if(ValidateUtils.isEmpty(obj)){
							obj =  Class.forName(field.getType().getName()).newInstance();
						}
						Field clazzField = getAccessibleField(obj, clazzAndField[1]); //获取类变量内的 属性
						Object value =valueManage(objValue,clazzField.getType());
						BeanUtils.setProperty(obj, clazzField.getName(), value); //给T中 类变量赋值
						objValue = obj;
					}
				}
				
				
				if(field == null){
					continue;
//					throw new  UnknownError("-----------["+key+"]  无对应 field 属性 --------------");
				}
				Object objvalue =valueManage(objValue,field.getType());
				BeanUtils.setProperty(t, field.getName(), objvalue); //给T中 类变量赋值
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return t;
	}


public static Map<String,Field> getAllFieldsMap(Object object){

	Assert.notNull(object,"'object' must not be empty.");
	Map<String,Field> clazzMaps = Maps.newHashMap();
	//The statement storage

	List<Class<?>> clazzs = getClasses(object);
	for(Class<?> clazz : clazzs){
		
		Field[] fields =clazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		for(Field field : fields){
			clazzMaps.put(field.getName(), field); //save field to map
		}
	}

	return clazzMaps;
}
public static List<Class<?>> getClasses(Object object){

	List<Class<?>> clazzs = Lists.newArrayList();

	//add Class to List
	for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {

		clazzs.add(superClass);

	}
	return clazzs;
}
}
