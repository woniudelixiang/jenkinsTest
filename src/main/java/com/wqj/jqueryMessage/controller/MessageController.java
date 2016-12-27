package com.wqj.jqueryMessage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/message")
public class MessageController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		
		return "/jqueryMessage/list";
	}
	
	@RequestMapping("/msgbox")
	public String msgbox() {
		
		return "/jqueryMessage/msgbox";
	}
}
