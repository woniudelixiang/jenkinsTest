package com.wqj.promise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/promise")
public class PromiseController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		return "promise/list";
	}
}
