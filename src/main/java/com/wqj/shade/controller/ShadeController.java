package com.wqj.shade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/shade")
public class ShadeController extends CommonController {

	// 跳转到遮罩演示页面
	@RequestMapping("/list")
	public String list() {
		return "shade";
	}
}
