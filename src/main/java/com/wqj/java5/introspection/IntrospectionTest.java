package com.wqj.java5.introspection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class IntrospectionTest {

	public static void main(String[] args) throws Exception {
		JavaBean javaBean = new JavaBean(3,5);
//		PropertyDescriptor pd =  new PropertyDescriptor("x", JavaBean.class);
//		Method setMethod = pd.getWriteMethod(); // 获取set方法
//		setMethod.invoke(javaBean, 10);
		
//		Method getMethod = pd.getReadMethod(); // 获取get方法
//		Object value = getMethod.invoke(javaBean);
//		System.out.println("value: " + value);
		
		BeanInfo beanInfo = Introspector.getBeanInfo(JavaBean.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			if(pd.getName().equals("x")){
				Method getMethod = pd.getReadMethod(); // 获取get方法
				Object value = getMethod.invoke(javaBean);
				System.out.println("value: " + value);
				break;
			}
		}
	}
	
}
