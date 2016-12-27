package com.wqj.responseBody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JSONHelper;
import com.wqj.menuStudy.entity.Menu;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/testResponseBody")
public class TestResponseBodyController extends CommonController {

	@RequestMapping("/list")
	@ResponseBody
	public String list() {
		Menu menu = new Menu();
		menu.setLabel("我是中文，不处理我会乱码哦");
		return JSONHelper.toJson(menu);
	}
	
}
