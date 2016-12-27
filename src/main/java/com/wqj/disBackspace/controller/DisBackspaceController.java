package com.wqj.disBackspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/disBackspace")
public class DisBackspaceController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		return "disBackspace";
	}
	
}
