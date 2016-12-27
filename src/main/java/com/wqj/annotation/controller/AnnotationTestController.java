package com.wqj.annotation.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wqj.common.annotation.CurrentUser;
import com.wqj.common.controller.CommonController;
import com.wqj.gson.entity.User;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/ann")
public class AnnotationTestController extends CommonController {

	@ResponseBody
	@RequestMapping("/addSessionUser")
	public String addSessionUser(HttpSession session) {
		User user = new User("wqj",1);
		session.setAttribute("user", user);
		return "设置成功";
	}
	
	@ResponseBody
	@RequestMapping("/currentUserTest")
	public String currentUserTest(@CurrentUser User user) {
		return "" + user.toString();
	}
	
}
