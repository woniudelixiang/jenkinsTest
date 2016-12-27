package com.wqj.fastjson.controller;

import com.alibaba.fastjson.JSON;
import com.wqj.gson.entity.User;

public class FastjsonController {

	
	public static void main(String[] args) {
		User user = new User("王启军",1);
		String json =  JSON.toJSONString(user);
		System.out.println(json);
		
	}
	
	
	
}
