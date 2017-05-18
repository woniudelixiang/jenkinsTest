package com.wqj.common.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wqj.systemPermission.entity.SystemPermissionInfo;
import com.wqj.systemPermission.service.SystemPermissionService;

public class MenuListener implements ServletContextListener{

	private WebApplicationContext applicationContext = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("---通过监听获取菜单----");
		ServletContext sc = sce.getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		SystemPermissionService systemPermissionService = applicationContext.getBean(SystemPermissionService.class);
		// 一级菜单
		List<SystemPermissionInfo> menuList = systemPermissionService.getRootMenu();
		System.out.println("父菜单个数： "+menuList.size());
		sc.setAttribute("menuList", menuList);
		
		String contextPath = sc.getContextPath();
		
		// 数据库中所有需要控制权限的地址
		List<SystemPermissionInfo> findAll = systemPermissionService.findAll();
		List<String> permissionUrl = new ArrayList<String>();
		for (SystemPermissionInfo systemPermissionInfo : findAll) {
			permissionUrl.add(contextPath+"/"+systemPermissionInfo.getUrl());
		}
		System.out.println("数据库中所有需要控制权限的地址： " + permissionUrl.size());
		sc.setAttribute("permissionUrl", permissionUrl);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed ==============>>>>>>> ");
		ServletContext sc = sce.getServletContext();
		sc.removeAttribute("menuList");
	}

}
