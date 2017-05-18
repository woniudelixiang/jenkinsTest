package com.wqj.common.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wqj.common.tag.AnchorTag;
import com.wqj.common.utils.SpringBeanUtil;
import com.wqj.systemPermission.entity.SystemUserInfo;
import com.wqj.systemPermission.entity.SystemUserRoleInfo;
import com.wqj.systemPermission.service.SystemUserRoleService;

public class PermissionInterceptor implements HandlerInterceptor {

	private List<String> noLimitResource = new ArrayList<String>();
	private List<String> loginResource = new ArrayList<String>();
	
	/**
     * Handler执行之前调用这个方法 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//获取请求的URL  
        String url = request.getRequestURI(); 
        String contextPath = request.getContextPath();
        
        System.out.println("当前请求的URL： " + url + ",  " + contextPath);
        //访问公开的地址，直接放行  
        if(handleResource(noLimitResource,url, contextPath)){  
            return true;  // 不需要权限就可以访问的url，直接放行
        }
        
        //获取Session  
        HttpSession session = request.getSession();  
//        if(session==null){   // session过期了
//	    String message = "<script type='text/javascript' language='javascript'>"+
//				        	"$.prompt('您的账号已过期，请重新登录！', {"+
//				        		"title: '提示',"+
//				        		"buttons: { '确认': true, '取消': false },"+
//				        		"submit: function(e,v,m,f){"+
//				        			"if(v){"+
//				        					"window.location.href='/dao-study/sys/user/loginUI';"+
//				        				"}"+
//				        			"}"+
//				        	"});"+
//				        "</script>";
//        	HttpUtils.respWrite(response, message);
//        	return false;
//        }
        
        
        SystemUserInfo currentUser = (SystemUserInfo) session.getAttribute("CURRENT_USER");  
        if(currentUser != null){
        	
        	//访问主页面的可以放过
        	if(handleResource(loginResource,url, contextPath)){
        		System.out.println("访问主页面，直接放行的.................");
        		return true;
        	}
        	
        	
        	// 验证是否有权限访问
        	List<SystemUserRoleInfo> userRole = SpringBeanUtil.getBean(SystemUserRoleService.class).getUserRoleSystemUser(currentUser);
        	if(AnchorTag.isHavePermissionMenu(currentUser, userRole, url)){
        		 return true;
        	}else{
        		System.out.println("没有权限。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
                request.getRequestDispatcher("/sys/permission/noPower").forward(request, response);  
        		return false;
        	}
        }else{
        	  
        	
        	String message1 = "<script type='text/javascript' language='javascript'>"+
			        	"alert('您的账号已过期，请重新登录！！！')"+
			        "</script>";
        	
        	String message = "<jsp:include page='/common/footer.jsp'/><script type='text/javascript' language='javascript'>"+
        			"$.prompt('您的账号已过期，请重新登录！', {"+
        			"title: '提示',"+
        			"buttons: { '确认': true, '取消': false },"+
        			"submit: function(e,v,m,f){"+
        			"if(v){"+
        			"window.location.href='/dao-study/sys/user/loginUI';"+
        			"}"+
        			"}"+
        			"});"+
        			"</script>";
        	request.getRequestDispatcher("/sys/home/overdue").forward(request, response);  
        	
        	// 通过发送Http头，控制浏览器禁止缓存
//        	response.setHeader("pragma","no-cache");
//        	response.setHeader("cache-control","no-cache");
//        	response.setDateHeader("expires",-1);  //设置有效时间，数字为一个代表时间的long型整数
//
//        	response.setContentType("text/html; charset=UTF-8");
//       		PrintWriter writer = response.getWriter();
//       		writer.write(message1);
//       		writer.flush();
//       		writer.close();
			   	return false;
			   	
//			   	请求转发和重定向的区别
//			    [1].请求转发在服务器内跳转，只有一次请求一次响应  重定向两次请求两次响应
//			    [2]........内部操作，不会影响地址栏路径  重定向地址栏变化
//			    [3]........只能在当前站点内跳转    重定向可以跳出站内
//			    [4]........由request发起request.getRequestDispatcher("").forward(...,...);   重定向response.sendRedirect();
//			    [5]........"/"代表当前工程，服务器端路径    重定向"/"代表服务器根目录，客户端路径
//			    [6]........存在request域    从定向不存在request域
			   	
        	
        }
        
        //不符合条件的，跳转到登录界面  
//        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);  
//        response.sendRedirect(contextPath+"/sys/user/login");
//		return false;
	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	/**
	 * Handler执行完成之后调用这个方法 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}
	
	private boolean handleResource(List<String> noLimitResource, String url, String contextPath) {
		
		boolean isStaticResource = staticResource(url);
		if(isStaticResource){
			return isStaticResource;
		}else{
			// url包含过滤字符串，跳出
			for (String resource : noLimitResource) {
				resource = contextPath+resource.trim();
				System.out.println("=====================================");
				System.out.println("resource : " + resource + "    url : " + url);
				if (url != null && !"".equals(resource) && url.contains(resource) ) {
					return true;
				}
			}
		}
		
		// 根目录，跳出
		if(url.equals("/")){
			return true;
		}
		return false;
	}
	
	public boolean staticResource(String url){
		if(url.contains("/css/") || url.contains("/js/") || url.contains("/images/")){
			System.out.println("访问的是静态资源，放行了...");
			return true;
		}
		return false;
	}
	

	public List<String> getNoLimitResource() {
		return noLimitResource;
	}

	public void setNoLimitResource(List<String> noLimitResource) {
		this.noLimitResource = noLimitResource;
	}

	public List<String> getLoginResource() {
		return loginResource;
	}

	public void setLoginResource(List<String> loginResource) {
		this.loginResource = loginResource;
	}  
}
