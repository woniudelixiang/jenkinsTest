package com.wqj.jqueryDialog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/jqueryDialog")
public class JqueryDialogController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		return "jqueryDialog";
	}
}
