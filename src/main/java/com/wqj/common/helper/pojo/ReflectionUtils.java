package com.wqj.common.helper.pojo;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.joda.time.DateTime;
import org.springframework.core.CollectionFactory;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.wqj.common.helper.FieldValueResolverUtil;
import com.wqj.common.helper.pojo.resolver.FieldValueResolver;
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
	// 
	private static final Map<String,Method> CLAZZ_METHOD_MAP_CACHE = Maps.newHashMap();
	//类集合
	private static final Map<String,Class<?>> CLAZZ_MAP_CACHE = Maps.newHashMap();
	// 
	private static final String SEPARATOR = "\\.";
	// 
	private static final String TYPE_NAME_PREFIX = "class ";
	
	// 所有的解析器
	private static final List<FieldValueResolver> resolvers ;
	
	static{
		ConvertUtils.register(new Converter(){
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class arg0, Object arg1) {
				if(ValidateUtils.isEmpty(arg1) || arg1.getClass().getName().equals(DateTime.class.getName())){
					return arg1;
				}
				SimpleDateFormat sdf = new  SimpleDateFormat(DateUtils.PATTERN_DATETIME);
				return DateUtils.toDateTime(sdf.format(arg1));
			}
		}, DateTime.class);
		
		// 获取所有的解析器
		resolvers = FieldValueResolverUtil.getResolvers();
		
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
			} catch (NoSuchFieldException e) {
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
	 * 将map中的数据 填充到  实体（自动新建实体）,支持多级属性，多级属性使用"."
	 * @param map 
	 * @param clazz 被赋值的实体Class
	 * @return
	 */
	public static <T>T fillClassForMap(Map<String,Object> map, Class<T> clazz) {
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
	 * 将map中的数据 填充到  实体（自动新建实体）,支持多级属性，多级属性使用"."
	 * @param map 值map
	 * @param t 被填充对象
	 * @return
	 */
	public static <T>T fillClassForMap(Map<String,Object> map, T t) {
		try {
			for(String key : map.keySet()){
				// map中的key
				String[] keyArray = key.split(SEPARATOR);
				//当前key对应的value
				Object value = map.get(key);
				//递归 填充属性
				fillLoop(keyArray, t, 0, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return t;
	}
	
	/**
	 * 
	 * @param keyArray 变量数组
	 * @param obj 被填充的对象
	 * @param position 数组下标
	 * @param value  需要填充的最终值
	 * @return
	 * @throws Exception
	 */
	public static Object fillLoop(String[] keyArray, Object obj, int position, Object value) throws Exception {
		System.out.println("递归了： "+new Gson().toJson(obj));
		
		// 被填充的对象的className
		String objClassName = obj.getClass().getName();
		
		// 已经递归到最底层了
		if(position == keyArray.length-1){
			Field field = ReflectionUtils.FIELD_MAP_CACHE.get(objClassName+"_"+keyArray[position]);
			if(field == null){
				field = getAccessibleField(obj, keyArray[position]); //获取 类 属性内的 属性
				ReflectionUtils.FIELD_MAP_CACHE.put(objClassName+"_"+keyArray[position], field); //save field to cache
			}
			if(field == null){
				LoggerUtils.info(CLASS_NAME, "在" + objClassName + " 中未找到  '" + keyArray[position] + "' 属性");
				return obj;
			}
			
			// 给属性赋值
			BeanUtils.setProperty(obj, field.getName(), convertValue(value,field.getType())); 
			return obj;
		}
		
		//================================== 以下是多级属性的情况 =====================================================
		Map<String, Field> fieldMap = FIELDS_MAP_CACHE.get(objClassName);
		
		if(fieldMap == null){
			fieldMap = getAllFieldsMap(obj);
			FIELDS_MAP_CACHE.put(objClassName, fieldMap);
		}
		
		// 当前处理的属性名称
		String positionKey = keyArray[position];
		// 方括号中间指定的下标
		String positionKeyIndex = "";
		// 方括号左边的位置
		int bracketsLeftIndex = positionKey.lastIndexOf("[");
		if(bracketsLeftIndex >= 0){
			positionKey = positionKey.substring(0, bracketsLeftIndex);
			// 方括号左边的位置
			int bracketsRightIndex = keyArray[position].lastIndexOf("]");
			positionKeyIndex = keyArray[position].substring(bracketsLeftIndex+1, bracketsRightIndex);
		}
		
		// 获取当前处理属性的field
		Field field = fieldMap.get(positionKey);
		if(field == null){
			return obj;
		}
		
		// 当前处理属性的值
		Object fieldValue = field.get(obj); //获取此类变量值 (直接获取跳过 get 方法) 

		// 如果当前属性的值为null，则创建一个新对象作为当前属性的值
		if(fieldValue == null){
			fieldValue = newValue(field.getType());
		}
		
		for (FieldValueResolver resolver : resolvers) {
			if(resolver.isSupport(fieldValue.getClass())){
				resolver.fieldValueResolver(keyArray, obj, position, value, positionKeyIndex, field, fieldValue);
				break;
			}
		}
		return obj;
	}
	
	private static Object newValue(Class<?> type) {
		try {
			if (type.isArray()) {
				Class<?> componentType = type.getComponentType();
				// TODO - only handles 2-dimensional arrays
				if (componentType.isArray()) {
					Object array = Array.newInstance(componentType, 1);
					Array.set(array, 0, Array.newInstance(componentType.getComponentType(), 0));
					return array;
				}
				else {
					return Array.newInstance(componentType, 0);
				}
			}
			else if (Collection.class.isAssignableFrom(type)) {
				return CollectionFactory.createCollection(type, 16);
			}
			else if (Map.class.isAssignableFrom(type)) {
				return CollectionFactory.createMap(type, 16);
			}
			else {
				return type.newInstance();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	

	public static void main(String[] args) {
		// 如果没有注解的参数的类型不是简单类型而是复合类型，就是用类ModelAttributeMethodProcessor来解析：
		
		
		// 判断是否是简单类型的
//		boolean simpleProperty = org.springframework.beans.BeanUtils.isSimpleProperty(String.class);
//		System.out.println(simpleProperty);
//		String [] staff = new String[100];
//		boolean primitiveOrWrapper = ClassUtils.isPrimitiveOrWrapper(Integer.class);
//		System.out.println("primitiveOrWrapper:  " + primitiveOrWrapper);
		//isPrimitiveOrWrapper方法是判断参数类型是否是基本类或基本类的包装类 
		
		
		
//		System.out.println("=======>>>>>>>>   " + staff.getClass().getComponentType());
		// 也就是返回你数组里面装的数据的数据类型的类名称。
		
		
		
		
			Map<String,Object> map = Maps.newHashMap();
//			map.put("id", 12);
			
//			map.put("name", "123");
//			map.put("subject.subjectName", "56565");
//			map.put("subject.id", 56);
//			map.put("dateTime", new Timestamp(1483428382668L));
			map.put("subjects[0].id", 0);
//			map.put("subjects[0].subjectName", "0:name");
//			
			map.put("subjects[2].id", 2);
//			map.put("subjects[2].subjectName", "2:name");
//			
			map.put("subjects[1].id", 1);
//			map.put("subjects[1].subjectName", "1:name");
			
			long start = System.currentTimeMillis();
			Test1 book = ReflectionUtils.fillClassForMap(map, Test1.class);
			System.out.println(System.currentTimeMillis()-start);
			System.out.println(new Gson().toJson(book));
	}
	
	
	//获取单个方法
	public static Method getDeclaredMethod(Class<?> clazz,String methodName,Class<?>... classes) throws NoSuchMethodException, SecurityException{
		
		StringBuffer sb  = new StringBuffer();
		for(Class<?> argclazz : classes){
			sb.append(argclazz.getSimpleName()+"_");
		}
		Method method = CLAZZ_METHOD_MAP_CACHE.get(clazz.getName()+"_"+methodName+"_"+sb.toString());
		if(method == null){
			 method = clazz.getDeclaredMethod(methodName,classes);
			 CLAZZ_METHOD_MAP_CACHE.put(clazz.getName()+"_"+methodName+"_"+sb.toString(), method);
		}
		if(method != null){
			method.setAccessible(true);
		}
		return method;
	}
	
	

	//执行方法
	public static Object invokeMethod(Object obj,Method method,Object... args) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		return method.invoke(obj, args);
	}
	
	//创建实例
	public static Object newInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> fieldClazz = CLAZZ_MAP_CACHE.get(className);

		if( fieldClazz == null){
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
	public static Object convertValue(Object value,Class<?> typeClass) {
		return new ConvertUtilsBean().convert(value,typeClass);
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
