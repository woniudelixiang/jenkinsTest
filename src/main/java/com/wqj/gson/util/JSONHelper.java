package com.wqj.gson.util;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.wqj.common.util.pojo.Person;
import com.wqj.common.util.pojo.Student;
import com.wqj.common.util.pojo.User;

public class JSONHelper {
	
	/**
	 * 将JSON字符串转化成实体Bean对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return new Gson().fromJson(json, clazz);
	}

	/**
	 * 将实体Bean对象转化成JSON字符串
	 * @param jsonElement
	 * @return
	 */
	public static String toJson(Object jsonElement) {
		return new Gson().toJson(jsonElement);
	}
	
	/**
	 * 将javaBean转成javaBean（集合）
	 * @param javaBean
	 * @param clazz
	 * @return
	 */
	public static <T> T toMap(Object javaBean, Class<T> clazz) {
		return fromJson(toJson(javaBean), clazz);
	}
	
	/**
	 * 将javaBean转成javaBean（集合）
	 * @param javaBean
	 * @param clazz
	 * @return
	 */
	public static <T> T toMap(Gson gson, Object javaBean, Class<T> clazz) {
		return gson.fromJson(gson.toJson(javaBean), clazz);
	}
	
	public static void main(String[] args) {
		
		Student stu = new Student();
		stu.setName("昵称");
		User user = new User();
		user.setUsername("姓名");
		Person person = new Person();
		person.setNationality("国籍");
		user.setPerson(person);
		stu.setUser(user);
		
		Gson gson = new Gson();
		
		FilterExclusionStrategy ts = new FilterExclusionStrategy();
		ts.setClasses(new Class<?>[] {DateTime.class, Person.class});
		gson = new GsonBuilder().setExclusionStrategies(ts).create();
		String json = gson.toJson(stu);
		System.out.println(json);
	}
	
	/**
	 * 自定义实体Bean对象转化成JSON字符串
	 * 
	 * @param obj  被转化的对象
	 * @param fields  需要转换的字段
	 * @return  
	 */
	public static String toFilterJson(Object obj, String[] fields, boolean reverse){
		FilterInclusionStrategy ts = new FilterInclusionStrategy();
		ts.setFields(fields);
		Gson gson = new GsonBuilder().setExclusionStrategies(ts).create();
		return gson.toJson(obj);
	}
	
	/**
	 * 自定义实体Bean对象转化成JSON字符串
	 * 
	 * @param obj  被转化的对象
	 * @param fields  需要转换的字段
	 * @return  
	 */
	public static String toFilterJson(Object obj, boolean reverse){
		FilterInclusionStrategy ts = new FilterInclusionStrategy();
		ts.setReverse(reverse);
		Gson gson = new GsonBuilder().setExclusionStrategies(ts).create();
		return gson.toJson(obj);
	}
	
	/**
	 * 
	 * 自定义实体Bean对象转化成JSON字符串
	 * 
	 * @param obj  被转化的对象
	 * @param fields  需要转换的字段
	 * @param clazz  需要转换的类
	 * @return
	 */
	public static String toFilterJson(Object obj, String[] fields, Class<?>[] clazz){
		FilterInclusionStrategy ts = new FilterInclusionStrategy();
		ts.setClasses(clazz);
		ts.setFields(fields);
		Gson gson = new GsonBuilder().setExclusionStrategies(ts).create();
		return gson.toJson(obj);
	}
	
	/**
	 * 自定义实体Bean对象转化成JSON字符串
	 * 
	 * @param obj  被转化的对象
	 * @param fields  需要转换的字段
	 * @param clazz  需要转换的类
	 * @param reverse  是否 颠倒,相反
	 * @return
	 */
	public static String toFilterJson(Object obj, String[] fields, Class<?>[] clazz, boolean reverse){
		FilterInclusionStrategy ts = new FilterInclusionStrategy();
		ts.setClasses(clazz);
		ts.setFields(fields);
		ts.setReverse(reverse);
		Gson gson = new GsonBuilder().setExclusionStrategies(ts).create();
		return gson.toJson(obj);
	}
	
	 public static JsonArray toJsonArray(String jsonStr){  
		 JsonParser parser = new JsonParser();   
		 JsonArray JsonArray = parser.parse(jsonStr).getAsJsonArray();
		 return JsonArray;
	 }  
}
