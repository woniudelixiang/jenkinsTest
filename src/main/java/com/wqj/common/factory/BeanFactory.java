package com.wqj.common.factory;

public class BeanFactory {

	public static <T> T newInstance(Class<T> clazz) {
		try {
			T t = clazz.newInstance();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
