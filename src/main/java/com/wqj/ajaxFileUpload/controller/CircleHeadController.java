package com.wqj.ajaxFileUpload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value={"/circleHead"})
public class CircleHeadController extends CommonController {

	
	@RequestMapping(value="/list")
	public String list(){
		return "circleHead/list";
	}
	
}
