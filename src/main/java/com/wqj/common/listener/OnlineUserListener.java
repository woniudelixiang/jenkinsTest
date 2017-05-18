package com.wqj.common.listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import com.wqj.systemPermission.entity.SystemUserInfo;

public class OnlineUserListener implements ServletContextListener{

	// 当ServletContext创建的时候该方法会执行
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		Map<SystemUserInfo,HttpSession> onlineUserMap = new HashMap<SystemUserInfo,HttpSession>();
		sc.setAttribute("onlineUserMap", onlineUserMap);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

}
