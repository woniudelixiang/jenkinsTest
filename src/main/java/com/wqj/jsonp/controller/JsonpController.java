package com.wqj.jsonp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JSONHelper;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/jsonp")
public class JsonpController extends CommonController {
	
	@RequestMapping("/list")
	public String list() {
		return "jsonp";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/ajax")
	public String ajax(HttpServletRequest request){
		Map<String,String> map = Maps.newHashMap();
		map.put("username", "wqj");
		map.put("age", "12");
		//获取当前访问的url
		System.out.println(request.getRequestURI());
		System.out.println("-------->>>>>>>>>> "+request.getQueryString());
		String rollbackName = request.getParameter("methodName");
		System.out.println("rollbackName: "+rollbackName);
		String result =  rollbackName +"("+ JSONHelper.toJson(map)+")";
		System.out.println("result: "+result);
		return result;
	}
	
	
}
