package com.wqj.jqueryUI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;


@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/jqueryUI")
public class JqueryUIController extends CommonController {

	@RequestMapping("/choose")
	public String jqueryFileUpload() {
		return "jqueryUI/choose";
	}
	
	// 何军的遮罩层+加载
	@RequestMapping("/list")
	public String list() {
		System.out.println("    hj---->>>list   ");
		return "hj/list";
	}
	
	@RequestMapping("/user/list")
	@ResponseBody
	public String btn(){
		return "";
		
	}
	
	
}
