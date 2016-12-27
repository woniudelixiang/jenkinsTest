package com.wqj.jqueryCookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/jqueryCookie")
public class JqueryCookieController extends CommonController {
	
	// 跳转到jqueryCookie演示页面
	@RequestMapping("/list")
	public String list() {
		return "jqueryCookie/list";
	}
}
