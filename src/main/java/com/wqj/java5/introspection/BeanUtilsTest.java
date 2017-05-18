package com.wqj.java5.introspection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtilsTest {

	public static void main(String[] args) throws Exception {
		JavaBean javaBean = new JavaBean(3,5);
		BeanUtils.setProperty(javaBean, "x", "10");
		System.out.println(javaBean);
		
		PropertyUtils.setProperty(javaBean, "x", 25);
		System.out.println(javaBean);
	}
	
}
