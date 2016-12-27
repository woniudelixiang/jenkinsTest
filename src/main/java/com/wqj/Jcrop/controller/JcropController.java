package com.wqj.Jcrop.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/jcrop")
@SuppressWarnings("rawtypes")
public class JcropController extends CommonController {

	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		
		return "Jcrop/list";
	}
	
	
	@ResponseBody
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request) {
		
		
		
		return "";
	}
	
	
	
	
}
