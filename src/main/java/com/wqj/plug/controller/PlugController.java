package com.wqj.plug.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/plug")
public class PlugController extends CommonController {
	@RequestMapping("/countdown")
	public String countdown(HttpServletRequest request) {
		//开始时间
		long startTime = 1463803200000L;
		//结束时间
		long endTime = 1463810400000L;
		//当前系统时间
		long now  = new Date().getTime();
		
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("now", now);
		return "plug/countdown";
	}
	
	@RequestMapping("/inputLimt")
	public String inputLimt() {
		
		return "plug/input_limt";
	}
	
	@RequestMapping("/recordTopRoll")
	public String recordTopRoll() {
		
		return "plug/record_top_roll";
	}
	
	@RequestMapping("/sweet")
	public String sweet() {
		
		return "plug/sweet";
	}
	
	@RequestMapping("/sweet2")
	public String sweet2() {
		
		return "plug/sweet2";
	}
	
	@RequestMapping("/sweet3")
	public String sweet3() {
		
		return "plug/sweet3";
	}
	
	
	@RequestMapping("/crop")
	public String crop() {
		
		return "plug/crop";
	}
	
	
	@RequestMapping("/serialize")
	public String serialize() {
		
		return "serialize";
	}
	
//	@ResponseBody
//	@RequestMapping("/paramBading")
//	public String paramBading(@RequestJsonParam String param) {
//		Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<User>>() {}.getType();
//        List<User> userList = gson.fromJson(param, type);
//        System.out.println("--------->>>>>>> " + userList.size()) ;
//		for (User user : userList) {
//			System.out.println(user.toString());
//		}
//		return "success";
//	}
	
//	@ResponseBody
//	@RequestMapping("/paramBading")
//	public String paramBading(@RequestBody String param) {
//		Gson gson = new Gson();
//		Type type = new TypeToken<ArrayList<User>>() {}.getType();
//		List<User> userList = gson.fromJson(param, type);
//		System.out.println("--------->>>>>>> " + userList.size()) ;
//		for (User user : userList) {
//			System.out.println(user.toString());
//		}
//		return "success";
//	}
	
	
	
	
	
}
