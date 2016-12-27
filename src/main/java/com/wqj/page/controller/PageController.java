package com.wqj.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/page")
public class PageController extends CommonController{

	@RequestMapping("/list")
	public String list(){
		//userService.find
		
		return "page/list";
	}
	
	
	
	
}
