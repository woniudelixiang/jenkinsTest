package com.wqj.systemPermission.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;
import com.wqj.systemPermission.entity.SystemPermissionInfo;

@Controller
@RequestMapping("/sys/home")
public class SystemHomeController extends CommonController{
	
	@RequestMapping("/overdue")
	public String overdue(HttpServletRequest request) {
		
		return "/systemPermission/overdue";
	}
	
	@RequestMapping("main")
	public String main(HttpServletRequest request) {
		
		return "/systemPermission/main";
	}
	
	
	@RequestMapping("top")
	public String top(HttpServletRequest request) {
		
		return "/systemPermission/common/top";
	}
	
	@RequestMapping("left")
	public String left(HttpServletRequest request) {
		List<SystemPermissionInfo> menuList = systemPermissionService.getRootMenu();
//		ServletContext sc = ServletContextUtil.getServletContext();
//		List<SystemPermissionInfo> menuList = (List<SystemPermissionInfo>) sc.getAttribute("menuList");
		
		request.setAttribute("menuList", menuList);
		return "/systemPermission/common/left";
	}
	
	@RequestMapping("right")
	public String right(HttpServletRequest request) {
		
		return "/systemPermission/common/right";
	}
	
	@RequestMapping("botton")
	public String botton(HttpServletRequest request) {
		
		return "/systemPermission/common/botton";
	}
	
}
