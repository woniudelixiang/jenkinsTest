package com.wqj.systemPermission.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/sys/user/role")
public class SystemUserRoleController extends CommonController{

	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		
		return "";
	}
	
}
