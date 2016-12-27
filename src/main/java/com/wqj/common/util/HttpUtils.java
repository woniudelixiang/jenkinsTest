package com.wqj.common.util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	
	private static final String CLASS_NAME = HttpUtils.class.getName();
	private static int TIMEOUT = 10 * 1000;
	private static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";
	private static String HTTP_HEAD_CONTENT_TYPE_VALUE = "application/json; charset=utf-8";
	
	/**
	 * 
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void respWrite(HttpServletResponse response, String jsonStr)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		LoggerUtils.info(CLASS_NAME, "json==>"+jsonStr);
		writer.write(jsonStr);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void respJsonpWrite(HttpServletResponse response, HttpServletRequest request, String jsonStr)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		String callbackFunName = request.getParameter("callbackparam");
		StringBuffer sb = new StringBuffer(callbackFunName);
		
		sb.append("(").append(jsonStr).append(")");
		
		PrintWriter writer = response.getWriter();
		writer.write(sb.toString());
		writer.flush();
		writer.close();
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getReqJson(HttpServletRequest request)
			throws Exception {
		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String str;
		while ((str = reader.readLine()) != null) {
			buffer.append(str);
		}
		reader.close();

		return buffer.toString();
	}
	
	/**
	 * Get HttpClient
	 * 
	 * @return
	 */
	private static HttpClient getHttpClient() {
		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
		return client;
	}
	
	/**
	 * Use Http to post message
	 * 
	 * @param url
	 * @param request
	 * @return
	 */
	public static String post(String url, String request) {
		LoggerUtils.debug(CLASS_NAME, String.format("Http Status url = [%s]", url));
		LoggerUtils.debug(CLASS_NAME, String.format("Http Status request = [%s]", request));
		HttpClient client = getHttpClient();
		String response = null;
		try {
			HttpPost post = getHttpPost(url, request);
			HttpResponse res = client.execute(post);
			int statusCode = res.getStatusLine().getStatusCode();
			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = [%s]", statusCode));
			if (statusCode == 200) {
				response = EntityUtils.toString(res.getEntity(), "UTF-8");
			} else {

			}
		} catch (Exception e) {
			LoggerUtils.error(CLASS_NAME, "Http Component Send Post Request Execption.", e);
		}
		return response;
	}

	/**
	 * Create HttpGet
	 * 
	 * @param url
	 * @return
	 */
	private static HttpGet getHttpGet(String url) {
		HttpGet get = new HttpGet(url);
		return get;
	}
	/**
	 * Use HttpGet to get response message
	 * 
	 * @param url
	 * @return
	 */
//	public static String get(String url) {
//		HttpClient client = getHttpClient();
//		LoggerUtils.debug(CLASS_NAME, url);
//		HttpGet get = getHttpGet(url);
//		String response = null;
//		try {
//			HttpResponse res = client.execute(get);
//			int statusCode = res.getStatusLine().getStatusCode();
//			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = %s", statusCode));
//			if (statusCode == 200) {
//				response = EntityUtils.toString(res.getEntity(), "UTF-8");
//			} else {
//			}
//		} catch (Exception e) {
//			LoggerUtils.error(CLASS_NAME, "Http Component Send Get Request Execption.", e);
//		}
//		return response;
//	}
	
	/**
	 * Get HttpClient
	 * 
	 * @return
	 */
//	private static HttpClient getHttpClient() {
//		HttpClient client = new DefaultHttpClient();
//		HttpParams httpParams = client.getParams();
//		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
//		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
//		return client;
//	}
	
	/**
	 * Use Http to post message
	 * 
	 * @param url
	 * @param request
	 * @return
	 */
//	public static String post(String url, String request) {
//		LoggerUtils.debug(CLASS_NAME, String.format("Http Status url = [%s]", url));
//		LoggerUtils.debug(CLASS_NAME, String.format("Http Status request = [%s]", request));
//		HttpClient client = getHttpClient();
//		String response = null;
//		try {
//			HttpPost post = getHttpPost(url, request);
//			HttpResponse res = client.execute(post);
//			int statusCode = res.getStatusLine().getStatusCode();
//			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = [%s]", statusCode));
//			if (statusCode == 200) {
//				response = EntityUtils.toString(res.getEntity(), "UTF-8");
//			} else {
//
//			}
//		} catch (Exception e) {
//			LoggerUtils.error(CLASS_NAME, "Http Component Send Post Request Execption.", e);
//		}
//		return response;
//	}
	
	
	/**
	 * Get the HttpPost info
	 * 
	 * @param url
	 * @param contextString
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static HttpPost getHttpPost(String url, String contextString) throws UnsupportedEncodingException {
		HttpPost post = new HttpPost(url);
		post.setHeader(HTTP_HEAD_CONTENT_TYPE, HTTP_HEAD_CONTENT_TYPE_VALUE);
		post.setEntity(new StringEntity(contextString,"UTF-8"));
		return post;
	}

	/**
	 * Use HttpGet to get response message
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpClient client = getHttpClient();
		LoggerUtils.debug(CLASS_NAME, url);
		HttpGet get = getHttpGet(url);
		String response = null;
		try {
			HttpResponse res = client.execute(get);
			int statusCode = res.getStatusLine().getStatusCode();
			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = %s", statusCode));
			if (statusCode == 200) {
				response = EntityUtils.toString(res.getEntity(), "UTF-8");
			} else {
			}
		} catch (Exception e) {
			LoggerUtils.error(CLASS_NAME, "Http Component Send Get Request Execption.", e);
		}
		return response;
	}
}
	
	
	/**
	 * Create HttpGet
	 * 
	 * @param url
	 * @return
	 *//*
	private static HttpGet getHttpGet(String url) {
		HttpGet get = new HttpGet(url);
		return get;
	}*/