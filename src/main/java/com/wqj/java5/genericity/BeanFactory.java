package com.wqj.java5.genericity;

public class BeanFactory {
	
	/**
	 * 泛型方法
	 * @param clazz 泛型T的Class对象
	 * @return 创建泛型T代表的类的对象
	 * @throws Exception
	 */
	public static <T> T getInstance(Class<T> clazz) throws Exception{
		// 创建泛型对象
		return clazz.newInstance();
	}
	
	
	public static void main(String[] args) {
		try {
			// 使用泛型方法，和普通方法一样，这里好处就是不用做类型强转
			StringBuffer sb = BeanFactory.getInstance(StringBuffer.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
