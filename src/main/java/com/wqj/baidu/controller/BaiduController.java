package com.wqj.baidu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/baidu")
public class BaiduController extends CommonController {

	
	// 跳转到图片上传页面
	@RequestMapping("/list")
	public String list() {
		
		return "baiduUpload";
	}
	
	
	
}
