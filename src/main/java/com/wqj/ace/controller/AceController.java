package com.wqj.ace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/ace")
public class AceController extends CommonController{

		@RequestMapping("/index")
		public String index(){
			return "ace/index";
		}
}
