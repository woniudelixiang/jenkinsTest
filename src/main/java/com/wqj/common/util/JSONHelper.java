package com.wqj.common.util;

import javax.servlet.ServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wqj.common.Const;

public class JSONHelper {
	
	/**
	 * 将实体Bean对象转化成JSON字符串
	 * @param jsonElement   实体Bean对象
	 * @return  JSON字符串
	 */
	public static String toJson(Object jsonElement) {
		Gson gson = getGsonBuilder().create();
	    String jsonStr = gson.toJson(jsonElement);
	    System.out.println("jsonStr: " + jsonStr);
	    return jsonStr;  
	}
	
	/**
	 * 将JSON字符串转化成实体Bean对象
	 * @param json  JSON字符串
	 * @param classOfT  Bean的class
	 * @return  Bean对象
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		// GsonBuilder gsonBuilder = getGsonBuilder().setPrettyPrinting();  // 对json结果格式化.  
		Gson gson = getGsonBuilder().create();
		return gson.fromJson(json, classOfT);
	}

	/**
	 * 将JSON字符串转化成泛型的数据集合
	 * @param json
	 * @param typeToken
	 * @return
	 */
	public static <T> T fromJson(String json, TypeToken<T> typeToken) {
		Gson gson = getGsonBuilder().create();
		return gson.fromJson(json, typeToken.getType());
	}
	
	/**
	 * 获取带日期格式化的 GsonBuilder
	 * @return
	 */
	public static GsonBuilder getGsonBuilder() {
		WebApplicationContext webApplicationContext = 
				ContextLoader.getCurrentWebApplicationContext(); 
		ServletContext sc = webApplicationContext.getServletContext(); 
		GsonBuilder gsonBuilder = (GsonBuilder) sc.getAttribute(Const.GSOONBUILDER);
		return gsonBuilder;
	}
}
