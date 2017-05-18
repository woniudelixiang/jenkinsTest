package com.wqj.common.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpSessionUtil {

	private static HttpSession session;
	
	public static HttpSession getHttpSession(){
		if(session == null){
			session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(false);
		}
		return session;
	}
}
