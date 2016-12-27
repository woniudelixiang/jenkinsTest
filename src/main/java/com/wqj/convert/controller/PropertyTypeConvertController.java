package com.wqj.convert.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.daoDemo.entity.User;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value={"/convert"})
public class PropertyTypeConvertController extends CommonController {

	@ResponseBody
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request) throws Exception{
		
		Timestamp value = new Timestamp(System.currentTimeMillis());
		User user = new User();
//		Field field = user.getClass().getDeclaredField("createDateTime");
//		field.setAccessible(true);
		BeanUtils.setProperty(user, "createDateTime" , value);
		System.out.println(user.getCreateDateTimeStr());
		
		return "";
	}
	
}
