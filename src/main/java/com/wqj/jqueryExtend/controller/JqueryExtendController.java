package com.wqj.jqueryExtend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/extend")
public class JqueryExtendController {

	@RequestMapping("/list")
	public String list() {
		return "jqueryExtend/list";
	}
	
}
