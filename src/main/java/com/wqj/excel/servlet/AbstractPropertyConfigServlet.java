package com.wqj.excel.servlet;

import org.springframework.web.servlet.HttpServletBean;

/**
 * 
 * @author LE CHEN
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractPropertyConfigServlet extends HttpServletBean{

	public final static String DEFAULT_CONFIG_LOCATION= "/WEB-INF/excelContext.xml"; 


	private String contextConfigLocation;
	
	
	/**
	 * @return the contextConfigLocation
	 */
	public String getContextConfigLocation() {
		return contextConfigLocation;
	}

	/**
	 * @param contextConfigLocation the contextConfigLocation to set
	 */
	public void setContextConfigLocation(String contextConfigLocation) {
		System.out.println("~~~~~~~~~~~~~~~~~~ setContextConfigLocation ~~~~~~~~~~~~~~~~~~~~~~~");
		this.contextConfigLocation = contextConfigLocation;
	}
	
	
	
	
	public String getDefaultConfigLocation(){
		return contextConfigLocation == null ?DEFAULT_CONFIG_LOCATION : contextConfigLocation ;
	}
	
	
	
	
}
