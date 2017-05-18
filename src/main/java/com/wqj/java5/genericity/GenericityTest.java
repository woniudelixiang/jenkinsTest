package com.wqj.java5.genericity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenericityTest {
	
	
//	public static void main(String[] args) {
//		
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:tt.xml");
//		applicationContext.getBean(GenericityTest.class);
//		
//		// 在使用的时候指定ArrayList类中的泛型为String类型
//		List<String> list = new ArrayList<String>();
//		
//		// 由于指定了list容器中的泛型为String类型 ，所以可以将字符串"abc"添加进去
//		list.add("abc");
//		
//		// 添加不是String类型的值，在编译的时候就会报错
//		// list.add(1);
//		
//		// 在使用的时候指定HashMap类中的key泛型为String类型,value泛型为Object类型
//		Map<String,Object> map = new HashMap<String,Object>();
//		// key为String，value为Object类型的子类可以存放
//		map.put("key1", "");
//		// key为int类型，编译器会报错
//	    // map.put(1, "");
//	}

}
