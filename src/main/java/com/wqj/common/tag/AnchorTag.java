package com.wqj.common.tag;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

import com.wqj.common.utils.HttpSessionUtil;
import com.wqj.common.utils.ServletContextUtil;
import com.wqj.common.utils.SpringBeanUtil;
import com.wqj.systemPermission.entity.SystemRolePermissionInfo;
import com.wqj.systemPermission.entity.SystemUserInfo;
import com.wqj.systemPermission.entity.SystemUserRoleInfo;
import com.wqj.systemPermission.service.SystemUserRoleService;

@SuppressWarnings("serial")
public class AnchorTag extends BodyTagSupport {

	// 当前登录用户
	private SystemUserInfo currentUser = (SystemUserInfo)HttpSessionUtil.getHttpSession().getAttribute("CURRENT_USER");
	
	private String href;
	private String onclick;
	
	@Override
	public int doStartTag() throws JspException {
		System.out.println("************  doStartTag()  **************");
		return EVAL_BODY_BUFFERED;  // http://qtxlw.iteye.com/blog/638442
		
//		return EVAL_BODY_INCLUDE;  // 会接着执行自定义标签的标签体
//		return SKIP_BODY;  // 忽略自定义标签的标签体
//		if(false){
//			return EVAL_BODY_INCLUDE;  // 会接着执行自定义标签的标签体
//		}else{
//			return SKIP_BODY;  // 忽略自定义标签的标签体
//		}
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		System.out.println("************  doEndTag()  **************");
		// 获取a标签的文本
		//获取body中的内容  
	    BodyContent bc = this.getBodyContent(); 
	    String bodyString = bc.getString();  
	    System.out.println("bodyString: " + bodyString);
		try {
			// 判断是否有权限
			
			List<SystemUserRoleInfo> userRole = SpringBeanUtil.getBean(SystemUserRoleService.class).getUserRoleSystemUser(currentUser);
			
//			if(currentUser.isHavePermissionMenu(href)){
				if(isHavePermissionMenu(currentUser, userRole, href)){
				String appHref = " href = \"" + href + "\"";
				String appOnclick = " onclick = \"" + onclick + "\"";
				
				out.print("<a " + appHref + "    " + appOnclick + "      >" + bodyString + "</a>");
//				out.print("<a href=" + href + " onclick = " + onclick + ">" + bodyString + "</a>");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return super.doStartTag();

//		 return EVAL_PAGE;    // 会接着执行JSP页面中位于结束标记后面的JSP代码

		// return SKIP_PAGE;  // 会忽略JSP页面中位于结束标记后面的所有内容
	}
	
	
	@SuppressWarnings("unchecked")
	public static boolean isHavePermissionMenu(SystemUserInfo currentUser, List<SystemUserRoleInfo> roles, String url){
		if(currentUser.getUserType()==2){ // 超级管理员拥有所有的权限
			return true;
		}
		url = url.replace("UI", "");
		
		// 不是数据库控制的 权限，即登录后就有的默认权限
		ServletContext sc = ServletContextUtil.getServletContext();
		
		Collection<String> permissionUrl = (List<String>) sc.getAttribute("permissionUrl");
		System.out.println(permissionUrl.toString());
		if(!permissionUrl.contains(url)){
			return true;
		}else{
			for (SystemUserRoleInfo userRole : roles) {
				List<SystemRolePermissionInfo> permissions = userRole.getSystemRole().getPermissions();
				for (SystemRolePermissionInfo rolePermission : permissions) {
					if(StringUtils.isNotBlank(rolePermission.getSystemPermission().getUrl())){
						System.out.println("url : " + url.replace("UI", ""));
						System.out.println("getUrl() : " + rolePermission.getSystemPermission().getUrl());
						System.out.println(url.replace("UI", "").contains(rolePermission.getSystemPermission().getUrl()));
						if(url.replace("UI", "").contains(rolePermission.getSystemPermission().getUrl())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	
	@Override
	public int doAfterBody() throws JspException {
		System.out.println("************  doAfterBody()  **************");
		
		return SKIP_BODY;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public SystemUserInfo getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(SystemUserInfo currentUser) {
		this.currentUser = currentUser;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
}
