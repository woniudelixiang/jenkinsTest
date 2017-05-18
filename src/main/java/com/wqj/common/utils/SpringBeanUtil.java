package com.wqj.common.utils;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBeanUtil {

	public static <T> T getBean(Class<T> clazz) {
		ServletContext sc = ServletContextUtil.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		T bean = applicationContext.getBean(clazz);
		return bean;
	}

}
