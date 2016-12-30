package com.wqj.common.helper;

import java.awt.print.Book;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import com.google.gson.Gson;
import com.wqj.common.helper.pojo.Bean;
import com.wqj.common.util.DateUtils;
import com.wqj.common.util.LoggerUtils;
import com.wqj.common.util.ValidateUtils;

/**
 * @author LE CHEN
 * 
 */
public class ReflectionUtils {
	private static final String CLASS_NAME = ReflectionUtils.class.getName();

	//属性集合缓存
	private static final Map<String,Map<String, Field>> FIELDS_MAP_CACHE = Maps.newHashMap();
	//属性缓存
	private static final Map<String,Field> FIELD_MAP_CACHE = Maps.newHashMap();
	//属性 类型 缓存
	private static final Map<String,List<String>> FIELD_TYPE_MAP_CACHE = Maps.newHashMap();
	//类集合
	private static final Map<String,Class<?>> CLAZZ_MAP_CACHE = Maps.newHashMap();

	private static final String SEPARATOR = "\\.";
	
	private static final String TYPE_NAME_PREFIX = "class ";

	
	

	/**
	 *  map 内数据 填充到  实体（自动新建实体）
	 * @author LE CHEN
	 * @param map 
	 * @param clazz 被赋值的实体Class
	 * @return
	 * 如果 实体内包括实体 请以 '实体内 类属性名称.类属性内的字段名称' 
	 */
	public static  <T> T fillClassForMap(Map<String,Object> map,Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
			fillClassForMap(map, t);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return t;
	}

	/**
	 * 
	 * 将 map 中 value 填充到 对象 t 中 （手动传入实体）
	 * 
	 * @param map 值map
	 * @param t 被填充对象
	 * @return
	 */
	public static  <T> T fillClassForMap(Map<String,Object> map,T t) {
		try {
			Map<String,Integer> key_map = new HashMap<String,Integer>(); //真实key
			for(String key : map.keySet()){
				//接收value 属性 value值
				Object obj_value = map.get(key); // sql字段值
				String[] clazz_field_array = key.split(SEPARATOR);
				//递归 赋值（ref 变量特性）
				fillLoop(clazz_field_array, t, 0, obj_value,key_map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return t;
	}
	
	
	
	
	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过get函数.
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);
		Assert.notNull(field, "在  [" + obj + "] 中无法找到  [" + fieldName + "] 属性");
		Object value = null;
		try {
			value = field.get(obj);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(CLASS_NAME, "getFieldValue[{}]", e);
		}
		return value;
	}
	
	/**
	 * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问, 如向上转型到Object仍无法找到, 返回null.
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Field getAccessibleField(final Object object, final String fieldName) {
		Assert.notNull(object, "'object' must not be empty.");
		Assert.hasText(fieldName, "fieldName");
		Field field = null;
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				// 返回包含public protected package private修饰的字段，即所有访问权限的字段。但不包括继承或实现的接口的字段,如果找不到抛NoSuchFieldException异常
				field = superClass.getDeclaredField(fieldName);
				// 设置可以访问
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// 当前类未定义,继续向上找
			}
		}
		return field;
	}
	
	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过set函数.
	 * @param obj
	 * @param fieldName
	 * @param value
	 */
	public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
		Field field = getAccessibleField(obj, fieldName);
		Assert.notNull(field, "在  [" + obj + "] 中无法找到  [" + fieldName + "] 属性");
		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(CLASS_NAME, "setFieldValue[{}]", e);
		}
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 
	 * @param clazz_field_array 变量数组
	 * @param obj 对象
	 * @param array_index 数组下标
	 * @param o_value  最终值
	 * @return
	 * @throws Exception
	 */
	public static  Object fillLoop(String[] clazz_field_array,Object obj,int array_index,Object o_value,Map<String,Integer> key_map) throws Exception {
		String obj_class_name = obj.getClass().getName();
		if(array_index  == clazz_field_array.length-1){
			Field clazz_field = ReflectionUtils.FIELD_MAP_CACHE.get(obj_class_name+"_"+clazz_field_array[array_index]);
			if(clazz_field == null){
				clazz_field = getAccessibleField(obj, clazz_field_array[array_index]); //获取 类 属性内的 属性
				ReflectionUtils.FIELD_MAP_CACHE.put(obj_class_name+"_"+clazz_field_array[array_index], clazz_field); //save field to cache
			}
			if(clazz_field == null){
				LoggerUtils.info(CLASS_NAME, "------------------"+obj_class_name+" 未找到  '"+clazz_field_array[array_index]+"' 属性--------------");
				//				throw new Exception("====== "+clazz_field_array[array_index]+" 未找到========");
				return obj;
			}
			
//			System.out.println(clazz_field_array[array_index]+"===:"+o_value);
			Object value = valueManage(o_value,clazz_field.getType()); //格式化
			BeanUtils.setProperty(obj, clazz_field.getName(), value); //给 属性 赋值
			return obj;
		}

		Map<String, Field> field_map = FIELDS_MAP_CACHE.get(obj_class_name);
		if(field_map == null){
			field_map = getAllFieldsMap(obj);
			FIELDS_MAP_CACHE.put(obj_class_name, field_map);
		}
		String clazz_field_name = clazz_field_array[array_index];
		
		if(clazz_field_name.indexOf("[") >= 0){
			clazz_field_name = clazz_field_name.substring(0, clazz_field_name.indexOf("["));
		}
		
		Field field = field_map.get(clazz_field_name);
		if( field  != null ){
			Object obj_value = field.get(obj); //获取此类变量值 (直接获取跳过 get 方法) book=>
			Type type = field.getGenericType();
			if(isAssignableFrom(type)){
				String a_key = clazz_field_array[array_index].substring(clazz_field_array[array_index].indexOf("[")+1, clazz_field_array[array_index].indexOf("]"));
				List<String> listParameterizedTypeStrArray = getListParameterizedTypeStrArray(type);
				if(listParameterizedTypeStrArray.size()  ==  1){
					Method method = obj_value.getClass().getDeclaredMethod("size");
					method.setAccessible(true);
					Object invoke_obj = method.invoke(obj_value);
					Integer size = (Integer)invoke_obj;
					int key_int = Integer.parseInt(a_key);
					Integer real_index = key_map.get(a_key);
					if(real_index != null){
						Method get_method = obj_value.getClass().getDeclaredMethod("get", int.class);
						get_method.setAccessible(true);
						invoke_obj = get_method.invoke(obj_value,real_index.intValue());
					}else{
						invoke_obj  = 	getInstanceByCache(listParameterizedTypeStrArray.get(0));
					}
					invoke_obj = fillLoop( clazz_field_array, invoke_obj,++array_index,o_value,key_map);
					if(real_index != null){
						
//						if(key_int < size){
//							
//							Method add_method = obj_value.getClass().getDeclaredMethod("add",new Class<?>[]{int.class,Object.class});
//							add_method.setAccessible(true);
//							add_method.invoke(obj_value,key_int, invoke_obj);
//							
////							Method remove_method = obj_value.getClass().getDeclaredMethod("remove",int.class);
////							remove_method.setAccessible(true);
////							remove_method.invoke(obj_value, real_index.intValue());
//							key_map.put(a_key, key_int);
//							System.out.println(a_key+" ==>"+key_map.get(a_key));
//						}else{
							Method set_method = obj_value.getClass().getDeclaredMethod("set",new Class<?>[]{int.class,Object.class});
							set_method.setAccessible(true);
							set_method.invoke(obj_value,real_index, invoke_obj);
//						}
					}else{
						Method add_method = obj_value.getClass().getDeclaredMethod("add",Object.class);
						add_method.setAccessible(true);
						add_method.invoke(obj_value, invoke_obj);
						key_map.put(a_key, size);
					}
					System.out.println("要被放置"+a_key+" ==> 现在被放置"+key_map.get(a_key)+" ==>"+size);
				}
				if(listParameterizedTypeStrArray.size()  ==  2){
					
				}
			}else{
				if(obj_value == null){
					obj_value  = 	getInstanceByCache(field.getType().getName());
				}
				obj_value = fillLoop( clazz_field_array, obj_value,++array_index,o_value,key_map);
			}
			Object value = valueManage(obj_value,field.getType()); //
			BeanUtils.setProperty(obj, field.getName(), value); //给T中 类变量赋值
		}
		return obj;
	}

	public static void main1(String[] args) {
		
//		ArrayList list = new ArrayList(3);
		
//		list.ensureCapacity(3);
//		list.set(1, 1);
//		System.out.println(list.size());

				Map<String,Object> map = Maps.newHashMap();
//				map.put("id", 12);
//				map.put("name", "123");
//				map.put("subject.subjectName", "56565");
//				map.put("subject.id", 56);
				
				map.put("subjects[0].id", 0);
				map.put("subjects[0].subjectName", "0:name");
				
				
				map.put("subjects[1].id", 1);
				map.put("subjects[1].subjectName", "1:name");
				
				map.put("subjects[2].id", 2);
				map.put("subjects[2].subjectName", "2:name");
				
				//		map.put("subject.book.name", "123");
				long start = System.currentTimeMillis();
				Book book = ReflectionUtils.fillClassForMap(map, Book.class);
//				System.out.println(System.currentTimeMillis()-start);
				System.out.println(new Gson().toJson(book));
//		ArrayList<ReflectionUtils> list = Lists.newArrayList();
//		Type[] parameterizedTypes = ReflectionUtils.getParameterizedTypes(list);
//		try {
//			Class<T> clazz = (Class<T>)ReflectionUtils.getClass(parameterizedTypes[0]);
//			System.out.println(clazz.getName());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			ReflectionUtils r = new ReflectionUtils();
//			Field field = r.getClass().getDeclaredField("list");
//			field.setAccessible(true);
//			Object object = field.get(r);
//			if(object == null){
//				Class<?> forName = Class.forName(field.getType().getName());
//				object = forName.newInstance();
//			}
//			
//			System.out.println(object);
//			Type t = field.getGenericType();  
//			getListParameterizedTypeStrArray(t);
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		String str = "sdsf[0].";
//		System.out.println(str.indexOf("["));
//		System.out.println(str.substring(0,4));//前包含 后不 包含)
	}
	
	public static void main(String[] args) {
		Bean bean = new Bean();
		bean.setName("wqj");
		ReflectionUtils.setFieldValue(bean, "name",bean);
		System.out.println("------------------------------------");
	}
	
	/**
	 * 从缓存中获取实例，如果缓存没有就创建一个实例，并将实例放入缓存
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static Object getInstanceByCache(String className) throws Exception{
		Class<?> fieldClazz = CLAZZ_MAP_CACHE.get(className);
		if(fieldClazz == null){
			fieldClazz = Class.forName(className);
			CLAZZ_MAP_CACHE.put(className, fieldClazz); //装载缓存
		}
		return 	fieldClazz.newInstance();
	}

	/*** 
     * 获取List中的泛型 
     */  
    public static List<String> getListParameterizedTypeStrArray(Type type) throws NoSuchFieldException, SecurityException {
    	
    	List<String> list = new ArrayList<String>();
        if (isAssignableFrom(type)) {  
            for (Type t : ((ParameterizedType) type).getActualTypeArguments()) { 
            	list.add(getClassName(t));
            }  
        }  
        return list;
    }  
    
    
    
    public static boolean isAssignableFrom(Type type){
    	
    	return ParameterizedType.class.isAssignableFrom(type.getClass());
    }
    
    
    

	public static Class<?> getClass(Type type) 
			throws ClassNotFoundException {
		String className = getClassName(type);
		if (className==null || className.isEmpty()) {
			return null;
		}
		return Class.forName(className);
	}
	
	
	public static String getClassName(Type type) {
	    if (type==null) {
	        return "";
	    }
	    String className = type.toString();
	    if (className.startsWith(TYPE_NAME_PREFIX)) {
	        className = className.substring(TYPE_NAME_PREFIX.length());
	    }
	    return className;
	}


	public static Type[] getParameterizedTypes(Object object) {
		Type superclassType = object.getClass().getGenericSuperclass();
		if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
			return null;
		}
		return ((ParameterizedType)superclassType).getActualTypeArguments();
	}






	/**
	 * @author LE CHEN
	 * @param value
	 * @param typeClass 
	 * @return
	 * 
	 * The type conversion!
	 */
	public static Object valueManage(Object value,Class<?> typeClass) {

		ConvertUtilsBean convertUtils = new ConvertUtilsBean();
		value =convertUtils.convert(value,typeClass);
		ConvertUtils.register(new Converter(){
			@SuppressWarnings("unchecked")
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class arg0, Object arg1) {
				// TODO Auto-generated method stub

				if(ValidateUtils.isEmpty(arg1) ||arg1.getClass().getName().equals(DateTime.class.getName())){
					return arg1;
				}
				SimpleDateFormat sdf = new  SimpleDateFormat(DateUtils.PATTERN_DATETIME);
				return DateUtils.toDateTime(sdf.format(arg1));
				//				return DateUtils.toDateTime(arg1.toString());
			}

		}, DateTime.class);

		return value;
	}

	/**
	 * @author LE CHEN
	 * @param object
	 * @return
	 * 
	 * For such a Class, including the parent Class
	 */
	public static List<Class<?>> getClasses(Object object){

		List<Class<?>> clazzs = Lists.newArrayList();

		//add Class to List
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {

			clazzs.add(superClass);

		}
		return clazzs;
	}


	/**
	 * @author LE CHEN
	 * 
	 * Get all the attributes of these, including the parent class.
	 */
	public static List<Field> getAllFields(Object object){

		Assert.notNull(object,"'object' must not be empty.");

		//The statement storage
		List<Field> fieldList = Lists.newArrayList();

		List<Class<?>> clazzs = getClasses(object);
		for(Class<?> clazz : clazzs){

			Field[] fields =clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			fieldList.addAll(Arrays.asList(fields));

		}

		return fieldList;
	}


	/**
	 * @author LE CHEN
	 * 
	 * Get all the attributes of these, including the parent class.
	 */
	public static Map<String,Field> getAllFieldsMap(Object object){

		Assert.notNull(object,"'object' must not be empty.");
		Map<String,Field> clazzMaps = Maps.newHashMap();
		//The statement storage

		List<Class<?>> clazzs = getClasses(object);
		for(Class<?> clazz : clazzs){

			String clazzName = clazz.getName();

			Field[] fields =clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			for(Field field : fields){
				clazzMaps.put(field.getName(), field); //save field to map
				FIELD_MAP_CACHE.put(clazzName+"_"+field.getName(), field); //save field to cache
			}
		}

		return clazzMaps;
	}



}
