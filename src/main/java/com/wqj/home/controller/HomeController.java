package com.wqj.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/home")
public class HomeController extends CommonController {

	@RequestMapping()
	public String list(){
		return "home";
	}                  
	
}
