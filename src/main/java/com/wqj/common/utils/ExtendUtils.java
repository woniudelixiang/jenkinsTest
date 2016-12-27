package com.wqj.common.utils;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.wqj.common.utils.pojo.Person;
import com.wqj.common.utils.pojo.PersonBean;
import com.wqj.gson.util.JSONHelper;

/**
 * 将2个对象的内容合并到目标对象中，如果多个对象具有相同的属性，则后者会覆盖前者的属性值 (同 jquery extend方法)
 * ExtendUtil.extend([depth,] target , object1  , object2 );
 * 
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月14日 上午10:38:59
 */
@SuppressWarnings("unchecked")
public class ExtendUtils {
	
	private static Gson gson = new Gson();
    
    public static <T>T extend(T initObject, Object freeObject) throws Exception{
    	// 讲javaBean转换成map
    	Map<String,Object> initMap = JSONHelper.toMap(gson, initObject, Map.class);
    	Map<String,Object> freeMap = JSONHelper.toMap(gson, freeObject, Map.class);
    	// map浅层次合并
    	mapExtend(false, initMap, freeMap);
    	// 递归Map转对象
    	ConvertUtil.convertMap(initMap,initObject);
    	return initObject;
    }
	
	public static void main(String[] args) throws Exception {
		Person p1 = new Person(1,"clx");
		PersonBean p2 = new PersonBean(0,"wqj","爱好");
		Person extend = extend(true, null, p1, p2);
		System.out.println(extend);
		System.out.println(extend == p1);
	}
	
	/**
	 * @param target        返回对象的类类型
	 * @param initObject   原始数据的对象
	 * @param freeObject   自由传入数据的对象
	 * @return             返回两个对象的合并 
	 * @throws Exception
	 */
    public static <T>T extend(Class<T> target, T initObject, Object freeObject) throws Exception{
    	// 讲javaBean转换成map
    	Map<String,Object> initMap = JSONHelper.toMap(gson, initObject, Map.class);
    	Map<String,Object> freeMap = JSONHelper.toMap(gson, freeObject, Map.class);
    	// map浅层次合并
    	mapExtend(false, initMap, freeMap);
    	T newInstance = initObject;
    	if(target != null){
    		newInstance = target.newInstance();
    	}
    	// 递归Map转对象
    	ConvertUtil.convertMap(initMap,newInstance);
    	return newInstance;
    }

	/**
	 * @param depth        是否深度合并
	 * @param target        返回对象的类类型
	 * @param initObject   原始数据的对象
	 * @param freeObject   自由传入数据的对象
	 * @return             返回两个对象的合并 
	 * @throws Exception
	 */
    public static <T>T extend(boolean depth, Class<T> target, T initObject, Object freeObject) throws Exception{
    	// 讲javaBean转换成map
		Map<String,Object> initMap = JSONHelper.toMap(gson, initObject, Map.class);
    	Map<String,Object> freeMap = JSONHelper.toMap(gson, freeObject, Map.class);
    	// map合并
    	mapExtend(depth, initMap, freeMap);
    	T newInstance = initObject;
    	if(target != null){
    		newInstance = target.newInstance();
    	}
    	// 递归Map转对象
    	ConvertUtil.convertMap(initMap,newInstance);
    	return newInstance;
    }
    
    //====================================================== helper  ========================================================
//	public static Map<String,Object> mapExtend(boolean depth, Map<String,Object> initMap,Map<String,Object> freeMap) throws Exception{
//		Set<String> initKeySet = initMap.keySet();
//		for (String key : initKeySet) {
//			Object freeValue = freeMap.get(key);
//			Object initValue = initMap.get(key);
//			if(depth){
//				if((initValue instanceof Map) && (freeValue instanceof Map)){
//					freeValue = mapExtend(depth, (Map<String,Object>)initValue, (Map<String,Object>)freeValue);
//				}
//			}
//			if(null != freeValue){
//				initMap.put(key, freeValue);
//			}
//		}
//		return initMap;
//	}
//	
    public static Map<String,Object> mapExtend(boolean depth, Map<String,Object> initMap, Map<String,Object> freeMap) throws Exception{
		Set<String> initKeySet = initMap.keySet();
		Set<String> freeKeySet = freeMap.keySet();
		// 合并
		Set<String> unionSet = unionSet(initKeySet, freeKeySet);
		System.out.println("unionSet: " + initKeySet.toString());

		for (String key : unionSet) {
			Object freeValue = freeMap.get(key);
			Object initValue = initMap.get(key);
			if(depth){
				if((initValue instanceof Map) && (freeValue instanceof Map)){
					freeValue = mapExtend(depth, (Map<String,Object>)initValue, (Map<String,Object>)freeValue);
				}
			}
			
			if(null != freeValue){
				initMap.put(key, freeValue);
			}
		}
		
		return initMap;
	}
	
	public static <T> Set<T> unionSet(Set<T> s1, Set<T> s2){
		Set<T> unionSet = Sets.newHashSet();
		unionSet.addAll(s1);
		unionSet.addAll(s2);
		return unionSet;
	}
    
	public static void setGson(Gson gson) {
		ExtendUtils.gson = gson;
	}
	
}
