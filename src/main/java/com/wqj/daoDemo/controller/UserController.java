package com.wqj.daoDemo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.wqj.common.annotation.AuthorAnnotation;
import com.wqj.common.bean.Page;
import com.wqj.common.controller.CommonController;
import com.wqj.common.enums.AuthorName;
import com.wqj.common.orm.PropertyFilter;
import com.wqj.daoDemo.entity.User;

@Controller
@RequestMapping(value={"/user","/users"} ,method={RequestMethod.GET,RequestMethod.POST})
public class UserController extends CommonController{
	
	@RequestMapping(value="/longFormat")
	@AuthorAnnotation(authorName=AuthorName.wangqijun)
	public String longFormats(HttpServletRequest request){
		/*,Page page*/
		//int i = 1/0;
		System.out.println("~~~~~~~~~~    异常后续执行逻辑       ~~~~~~~~~~~~~~~~~~");
		return "longFormat";
	}
	
	
	@RequestMapping(value="/ajaxSubmit")
	@AuthorAnnotation(authorName=AuthorName.wangqijun)
	@ResponseBody
	public String ajaxSubmit(HttpServletRequest request) throws Exception{
		/*try {
			userService.getUserByUserName("s14");
			System.out.println("*************************************");
			
			int i = 1/0;
			System.out.println("~~~~~~~~~~   ajaxSubmit     ~~~~~~~~~~~~~~~~~~");
		} catch (Exception e) {
			userService.getUserByUserName("s14");
			//e.printStackTrace();
		}*/
		userService.getUserByUserName("s14");
		
		System.out.println("*************************************");
			
		return "success";
	}
	
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request){
		
		/*List<User> list = userService.findDatas("student.bigStudent.bsId",1L);
		for(User user : list){
			System.out.println(user.toString());
		}*/
		/*User addUser = new User();
		addUser.setUsername("add");
		addUser.setPassword("123456");
		userService.save(addUser);*/
		String s1 ="student.bigStudent.bsId";
		String s2 ="student.bigStudent.maxStudent.msId";
		Map<String, Object> map = Maps.newHashMap();
		map.put(s1, 1L);
		map.put(s2, 1L);
		List<User> list = userService.findDatas(map);
		for(User user : list){
			System.out.println(user.toString());
		}
		/*Page page = new Page();
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		List<User> list = userService.findPage(page, filters);
		for(User user : list){
			System.out.println(user.toString());
		}*/
		return "login";
	}
	
	@RequestMapping(value="/toLogin")
	public String toLogin(HttpServletRequest request,Page page){
		//Assert.notNull(criteria, "criteria is null.");
		//Assert.hasText(propertyNames, "propertyNames is required.");
		//构建PropertyFilter列表
//		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		List<User> list = null;//userService.findPage(page, filters);
		
		for(User user : list){
			System.out.println(user.toString());
		}
		return "login";
	}
	
	
	/*public static void main(String[] args) {
		testNotNull();
		System.out.println("继续执行");
	}*/
	
	public static String testNotNull(){
		User user = null;
		Assert.notNull(user, "user is null.");
		
		String result = "后续执行...";
		System.out.println(result);
		return result;
		
	}
	
	public static void main(String[] args) {
		String m = "abc";
		String t = "abc";
		System.out.println(m==t);
		System.out.println(m.equals(t));
	}
	
}
