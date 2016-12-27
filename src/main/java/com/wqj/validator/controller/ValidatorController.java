package com.wqj.validator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.validator.group.UserBeanGroup1;
import com.wqj.validator.pojo.UserBean;
import com.wqj.validator.pojo.VerifyBean;

/**
 * hibernate-validator后台验证 
 * 参考地址：http://blog.csdn.net/a491057947/article/details/46724707
 * @author Administrator
 */
@Controller
@RequestMapping("/validator")
public class ValidatorController extends CommonController {

	@RequestMapping("/list")
	public String list(){
		return "validator/list";
	}
	
	@RequestMapping("/submit")
	public String submit(@Validated(value={UserBeanGroup1.class}) UserBean userBean, BindingResult result,HttpServletRequest request){
		if(result.hasErrors()){
			FieldError fieldError = result.getFieldError("username");
			System.out.println(fieldError.getDefaultMessage());
			
			System.out.println("========================================");
			/*List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}*/
			
			request.setAttribute("result", result);
			return "validator/list";
		}
		
		System.out.println("没有错误");
		return "validator/list";
	}
	
	
	@RequestMapping("/verify")
	@ResponseBody
	public String verify(@Validated()VerifyBean verifyBean, BindingResult result){
		if(result.hasErrors()){
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}
		}
		
		return "";
	}
	
//	@ModelAttribute("user")
//	public User createModel() {
//		System.out.println("**** createModel  ***");
//	    return new User();
//	}
}
