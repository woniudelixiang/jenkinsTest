package com.wqj.angular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/angular")
public class AngularController extends CommonController {

	// 跳转到图片上传页面
	@RequestMapping("/list")
	public String list() {
		
		return "/angular/list";
	}
	
	@RequestMapping("/validator")
	public String validator() {
		
		return "/angular/validator";
	}
	
	@RequestMapping("/validate_form")
	public String validate_form() {
		
		return "/angular/validate_form";
	}
	
	
	
	
	
	
}
