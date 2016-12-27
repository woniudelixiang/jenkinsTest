package com.wqj.forms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;
import com.wqj.forms.pojo.ConditionData;

@Controller
@RequestMapping("/forms")
public class FormsController extends CommonController {

	@RequestMapping("/list")
	public String list(ConditionData cond, HttpServletRequest request) {
		request.setAttribute("cond", cond);
		return "forms/list";
	}
	
	@RequestMapping("/toEdit/{id}")
	public String toEdit( @PathVariable Long id, ConditionData cond, HttpServletRequest request) {
		request.setAttribute("cond", cond);
		return "forms/edit";
	}
	
	@RequestMapping("/edit")
	public String edit(ConditionData cond, HttpServletRequest request) {
		request.setAttribute("cond", cond);
		return forward("/list");
	}


}
