package com.wqj.passwordStrength.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/passwordStrength")
public class PasswordStrengthController extends CommonController{

	@RequestMapping("/list")
	public String list() {
		return "passwordStrength";
	}
}
