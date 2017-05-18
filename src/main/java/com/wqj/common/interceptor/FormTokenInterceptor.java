package com.wqj.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wqj.common.annotation.FormToken;
import com.wqj.common.helper.TokenHelper;

public class FormTokenInterceptor implements HandlerInterceptor {
	/**
	 * Handler执行之前调用这个方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("============= 表单token拦截器 =============" + request);
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			FormToken annotation = method.getAnnotation(FormToken.class);
			if (annotation != null) {
				HttpSession session = request.getSession();
				synchronized (session) {
					if (TokenHelper.validToken(request)) {
						return true;
					} else {
						Thread.sleep(3000);
						System.out.println("============= 表单重复提交了 =============");
						// String script =
						// "<script>alert('表单重复提交了!');</script>";
						// response.getOutputStream().write(script.getBytes("utf-8"));
						// 不符合条件的，跳转到XXX界面
						// Thread.sleep(1000*5);
						request.getRequestDispatcher("/WEB-INF/pages/forms/resubmit/formTokenError.jsp")
								.forward(request, response);

						return false;
					}
				}
			}
		}
		return true; // 当返回true时，处理执行链会继续, 当返回false时，则不会去执行Controller的方法
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

}
