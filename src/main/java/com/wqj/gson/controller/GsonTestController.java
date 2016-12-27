package com.wqj.gson.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JsonCycleFilterStrategy;
import com.wqj.gson.entity.SuperAppointmentInfo;
import com.wqj.gson.entity.User;
import com.wqj.gson.util.FilterExclusionStrategy;

@Controller
@RequestMapping("/gson")
public class GsonTestController extends CommonController{

	@ResponseBody
	@RequestMapping("/list")
	public String list(){
		SuperAppointmentInfo superAppointmentInfo = superAppointmentService.get(1L);
		// json循环过滤
		JsonCycleFilterStrategy cycleFilter = new JsonCycleFilterStrategy();
		
		Gson gson = new GsonBuilder()
			     .setExclusionStrategies(cycleFilter)
			     .setPrettyPrinting()
			     .create();
		String json = gson.toJson(superAppointmentInfo);
		System.out.println("json: " + json);
		
		return "";
	}
	
	
	public static void main(String[] args) {
		User user = new User(1L,"张三",1);
		// String json = JsonUtil.objectToJson(user);
		FilterExclusionStrategy filter = new FilterExclusionStrategy();
		 Gson gson = new GsonBuilder()
			     .setExclusionStrategies(filter)
			     .setPrettyPrinting()
			     .create();
		System.out.println(gson.toJson(user));
	}
	
	
	public static void typeToken(){
		Type type = new TypeToken<List<User>>() {}.getType();
		
		Gson gson = new GsonBuilder()
//		        .registerTypeAdapter(type, typeAdapter)
		        .create();
		
		List<User> list = new ArrayList<User>();
		list.add(new User("a",11));
		list.add(new User("b",22));
		//注意，多了个type参数
		String result = gson.toJson(list, type);
		String result2 = gson.toJson(list);
		System.out.println("result: " + result);
		System.out.println("result2: " + result2);
	}
	
	
}
