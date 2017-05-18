package com.wqj.java5.genericity;

import java.util.HashMap;

import com.wqj.daoDemo.service.UserService;

public class CommonService {

	private static HashMap<String, CommonService> serviceMap = new HashMap<String, CommonService>();

	public static <T extends CommonService> T getService1(Class<T> clazz) {
		T service = (T) serviceMap.get(clazz.getName());
		if (service == null) {
			// service = (T) ServiceLocator.getService(clazz.getName());
			serviceMap.put(clazz.getName(), service);
		}
		return service;
	}
//
//	public static <T> T getService2(Class<? extends CommonService> clazz) {
//		T service = (T) serviceMap.get(clazz.getName());
//		if (service == null) {
//			 service = (T) CommonService.getService(clazz.getName());
//			serviceMap.put(clazz.getName(), service);
//		}
//		return service;
//	}
	
//	现在知道了这两种极其相似的泛型方法的区别了吧？
//	第一种泛型方法：返回值和参数值的类型是一致，推荐使用;
//	第二种泛型方法：返回值和参数值的类型不是一致，请谨慎或避免使用。
//	public static void main(String[] args) {
//		NoticeService noticeService = CommonService.getService1(NoticeService.class);
//		//正确的使用第一种泛型方法，不会出现编译错误。         
//		NoticeService noticeService = CommonService.getService2(UserService.class);//不正确的使用第一种泛型方法，会出现编译错误。
//	}
	
}
