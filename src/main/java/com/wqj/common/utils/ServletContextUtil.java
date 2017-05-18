package com.wqj.common.utils;

import javax.servlet.ServletContext;

public class ServletContextUtil {

	private static ServletContext sc;
	
	public static ServletContext getServletContext(){
		if(sc == null){
			sc = HttpSessionUtil.getHttpSession().getServletContext();
		}
		return sc;
	}
}
