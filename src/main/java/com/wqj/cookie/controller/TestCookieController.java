package com.wqj.cookie.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value={"/testCookie"})
public class TestCookieController extends CommonController {
	
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		return "cookie/list";
	}
	
	@RequestMapping(value="/setCookie")
	public String setCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("name", "wqj");
		// 设定有效时间  以s为单位
		cookie.setMaxAge(6000) ;
		// 可在同一应用服务器内共享
//		cookie.setPath("/") ;
		// 域名要以".zl.org"开头共享
		//cookie.setDomain(".zl.org") ;
		// 发送Cookie文件
		response.addCookie(cookie) ;
		//读取Cookie
		
		return "cookie/list";
	}
	
	@RequestMapping(value="/getCookie")
	public String getCookie(HttpServletRequest request){
		 Cookie cookies[] = request.getCookies() ;
		if(cookies!=null){
			 for (Cookie cookie : cookies) {
				System.out.println("cookieName: " + cookie.getName() + "    cookieValue: " + cookie.getValue());	
				}
		}
		return "cookie/list";
	}
}
