package com.wqj.common.utils;

import java.util.Map;
import java.util.Set;

import com.wqj.common.utils.pojo.Person;
import com.wqj.gson.util.JSONHelper;

/**
 * 将多个对象的内容合并到目标对象中，如果多个对象具有相同的属性，则后者会覆盖前者的属性值 (同 jquery extend方法)
 * ExtendUtil.extend([depth,] target [, object1 ] [, objectN ]);
 * 
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月14日 上午10:38:59
 */
public class ExtendUtil {
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		Person p1 = new Person(1,"clx");
		Person p2 = new Person("wqj");
		Person extend = extend(Person.class, p1, p2);
		System.out.println(extend);
	}
	
	/**
	 * @param clazz        返回对象的类类型
	 * @param initObject   原始数据的对象
	 * @param freeObject   自由传入数据的对象
	 * @return             返回两个对象的合并 
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public static <T>T extend(Class<T> clazz, Object initObject, Object freeObject) throws Exception{
    	// 讲javaBean转换成map
    	Map<String,Object> initMap = JSONHelper.toMap(initObject, Map.class);
    	Map<String,Object> freeMap = JSONHelper.toMap(freeObject, Map.class);
    	extend(false, initMap, freeMap);
    	T newInstance = clazz.newInstance();
    	// 递归Map转对象
    	ConvertUtil.convertMap(initMap,newInstance);
    	return newInstance;
    }

	/**
	 * @param depth        是否深度合并
	 * @param clazz        返回对象的类类型
	 * @param initObject   原始数据的对象
	 * @param freeObject   自由传入数据的对象
	 * @return             返回两个对象的合并 
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public static <T>T extend(boolean depth, Class<T> clazz, Object initObject, Object freeObject) throws Exception{
    	// 讲javaBean转换成map
		Map<String,Object> initMap = JSONHelper.toMap(initObject, Map.class);
    	Map<String,Object> freeMap = JSONHelper.toMap(freeObject, Map.class);
    	extend(depth, initMap, freeMap);
    	T newInstance = clazz.newInstance();
    	// 递归Map转对象
    	ConvertUtil.convertMap(initMap,newInstance);
    	return newInstance;
    }
    
    //====================================================== helper  ========================================================
	@SuppressWarnings("unchecked")
	public static Map<String,Object> extend(boolean depth, Map<String,Object> initMap,Map<String,Object> freeMap) throws Exception{
		Set<String> initKeySet = initMap.keySet();
		for (String key : initKeySet) {
			Object freeValue = freeMap.get(key);
			Object initValue = initMap.get(key);
			if(depth){
				if((initValue instanceof Map) && (freeValue instanceof Map)){
					freeValue = extend(depth, (Map<String,Object>)initValue, (Map<String,Object>)freeValue);
				}
			}
			if(null != freeValue){
				initMap.put(key, freeValue);
			}
		}
		return initMap;
	}
}
