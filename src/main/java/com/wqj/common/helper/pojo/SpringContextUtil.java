package com.wqj.common.helper.pojo;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 * 获取spring容器，以访问容器中定义的其他bean 
 */  
public class SpringContextUtil implements ApplicationContextAware {  
   
    // Spring应用上下文环境  
    private static ApplicationContext applicationContext;  
   
    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
     */  
    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException  { 
    	System.out.println("============ 实现ApplicationContextAware接口的回调方法，设置上下文环境   ===============");
        SpringContextUtil.applicationContext = applicationContext;  
    }  
   
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
   
    /** 
     * 获取对象 
     * 这里重写了bean方法，起主要作用 
     * @param name 
     * @return Object 一个以所给名字注册的bean的实例 
     * @throws BeansException 
     */  
    public static Object getBean(String beanId) throws BeansException {  
        return applicationContext.getBean(beanId);  
    }  
    
	public static <T> Map<String, T> getBeans(Class<T> clazz) throws BeansException {  
    	return applicationContext.getBeansOfType(clazz);
    }
}