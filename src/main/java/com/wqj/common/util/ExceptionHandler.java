package com.wqj.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.wqj.common.annotation.AuthorAnnotation;
import com.wqj.common.enums.AuthorName;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	@SuppressWarnings("unused")
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		try {
			// Exception的具体说明
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ex.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();

			// 获得当前的IP地址
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();

			// 作者名称
			String authorName = getAuthorName(handler).getValue();
			// 模块（Controller）名称
			String modelName = getModelName(handler);
			// 方法名称
			String functionName = getFunctionName(handler);

			// 发送邮件
			System.out.println("发送邮件......................");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("errorMsg", "系统异常...");

			// 普通请求异常
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
					.getHeader("X-Requested-With") != null && request
					.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				// 对于非ajax请求，我们都统一跳转到error.jsp页面
				return new ModelAndView("/error", map);
			} else {
				// 如果是ajax请求，JSON格式返回
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write(JSONUtils.toJSONString(map));
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			
		}
		// 如果该方法返回了null，则Spring会继续寻找其他的实现了HandlerExceptionResolver接口的Bean。
		// 换句话说，Spring会搜索所有注册在其环境中的实现了HandlerExceptionResolver接口的Bean，逐个执行，
		// 直到返回了一个ModelAndView对象。
		return null;
	}

	/**
	 * 获取作者名称
	 * 
	 * @param handler
	 * @return
	 */
	public static AuthorName getAuthorName(Object handler) {
		AuthorName authorName = AuthorName.missing;
		if (handler instanceof HandlerMethod) {
			HandlerMethod h = (HandlerMethod) handler;
			Method method = h.getMethod();
			authorName = method.getAnnotation(AuthorAnnotation.class)
					.authorName();
		}
		return authorName;
	}

	/**
	 * 获取抛出异常的模块（Controller）名称
	 * 
	 * @param handler
	 * @return
	 */
	public static String getModelName(Object handler) {
		String modelName = "";
		if (handler instanceof HandlerMethod) {
			HandlerMethod h = (HandlerMethod) handler;
			modelName = h.getBeanType().getName();
		}
		return modelName;
	}

	/**
	 * 获取抛出异常的方法名称
	 * 
	 * @param handler
	 * @return
	 */
	public static String getFunctionName(Object handler) {
		String functionName = "";
		if (handler instanceof HandlerMethod) {
			HandlerMethod h = (HandlerMethod) handler;
			Method method = h.getMethod();
			functionName = method.getName();
		}
		return functionName;
	}
}
