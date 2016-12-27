package com.wqj.jqueryValidate.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.wqj.common.bean.CommonJsonBean;
import com.wqj.common.util.JSONHelper;
import com.wqj.common.utils.BeanUtil;
import com.wqj.gson.entity.User;

@Controller
@RequestMapping("/jqueryValidate")
public class JqueryValidateController {

	@RequestMapping("/demo1")
	public String demo1() {
		return "jqueryValidate/demo1";
	}
	
	/**
	 * 验证用户名是否存在
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/usernameIsExist")
	public String usernameIsExist(String username) {
		System.out.println("username: " + username);
		if("123".equals(username)){
			return "true";
		}
		return "false";
	}
	
	
	/**
	 * 验证用户输入的密码是否正确
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/passwordIsRight")
	public String passwordIsRight(String password,Long userId) {
		System.out.println("userId: " + userId + "password: " + password);
		
		if(BeanUtil.notEquals(1L, userId)){
			return "false";
		}
		
		if(BeanUtil.equals("123", password)){
			return "true";
		}
		
		return "false";
	}
	
	
	
	@RequestMapping("/iCheck")
	public String iCheck() {
		return "jqueryValidate/iCheck";
	}
	
	@RequestMapping("/select")
	public String select() {
		return "jqueryValidate/select";
	}
	
	@RequestMapping("/textarea")
	public String textarea() {
		return "jqueryValidate/textarea";
	}
	
	
	
	
	
	
	
	@RequestMapping("/main")
	public String main() {
		return "jqueryValidate/main";
	}
	
	@RequestMapping("/list1")
	public String list1() {
		return "jqueryValidate/list1";
	}
	@RequestMapping("/list1_1")
	public String list1_1() {
		return "jqueryValidate/list1_1";
	}
	
	@RequestMapping("/list2")
	public String list2() {
		return "jqueryValidate/list2";
	}
	@RequestMapping("/list2_1")
	public String list2_1() {
		return "jqueryValidate/list2_1";
	}
	
	@RequestMapping("/list3")
	public String list3() {
		return "jqueryValidate/list3";
	}
	@RequestMapping("/list3_1")
	public String list3_1() {
		return "jqueryValidate/list3_1";
	}
	
	@RequestMapping("/list4")
	public String list4() {
		return "jqueryValidate/list4";
	}
	@RequestMapping("/list4_1")
	public String list4_1() {
		return "jqueryValidate/list4_1";
	}
	
	@ResponseBody
	@RequestMapping("/remote")
	public String remote(String username) {
		System.out.println("username: " + username);
		if("123".equals(username)){
			return "true";
		}
		return "false";
	}
	
	@ResponseBody
	@RequestMapping("/ajax")
	public String ajax() {
		System.out.println("-----------ajax-------------");
		CommonJsonBean jsonBean = new CommonJsonBean();
		List<User> datas = Lists.newArrayList();
		datas.add(new User("wqj", 1));
		datas.add(new User("clx", 1));
		jsonBean.setDatas(datas);
		return JSONHelper.toJson(jsonBean);
	}
	
	
	@RequestMapping("/form")
	public String form() {
		System.out.println("-----------form-------------");
		return "jqueryValidate/success";
	}
	
}
