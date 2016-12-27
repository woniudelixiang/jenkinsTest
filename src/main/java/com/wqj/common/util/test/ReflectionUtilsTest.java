package com.wqj.common.util.test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Maps;
import com.wqj.common.util.ReflectionUtils;
import com.wqj.common.util.pojo.Student;

public class ReflectionUtilsTest {

	public static void main(String[] args) throws Exception {
		Map<String,Object> map = Maps.newHashMap();
		map.put("user.username", "clx");
		map.put("user.password", "123456");
		map.put("name", "wqj");
		
		try {
			Student convertMap = ReflectionUtils.convertMap(map, Student.class);
			
			//Student convertMap = convertMap(Student.class, map);
//			Student fieldVlaue = ReflectionUtils.convertMap(map, Student.class);
			
			System.out.println(convertMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static <T>T convertMap(Class<T> clazz, Map<String,Object> map) throws Exception{   
		// 获取类属性   
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
		T t = clazz.newInstance(); // 创建 JavaBean 对象   
		
		
		// 给 JavaBean 对象的属性赋值   
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println(propertyDescriptor.getName());
			String propertyName = propertyDescriptor.getName();
			
			
			
			if (map.containsKey(propertyName)) { 
				// 下面一句可以 try 起来,这样当一个属性赋值失败的时候就不会影响其他属性赋值.    
				Object value = map.get(propertyName);  
				System.out.println("~~~~~~~~~"+value);
				Object[] args = new Object[1];     
				args[0] = value;      
				propertyDescriptor.getWriteMethod().invoke(t, args);    
			}   
		}
		return t;  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * Map对象转化成 JavaBean对象
     * 
     * @param javaBean JavaBean实例对象
     * @param map Map对象
     * @return
     * @author jqlin
     */
    @SuppressWarnings({ "rawtypes","unchecked", "hiding" })
    public static <T> T map2Java(T javaBean, Map map) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	

}
