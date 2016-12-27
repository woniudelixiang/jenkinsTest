package com.wqj.common.orm.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public class SuperEntity<T> {

	/**
	 * 获得直接超类的泛型参数的实际类型（即直接父类的泛型信息 ）
	 * @return
	 */
	public Class<T> getEntityClass() {
		try {
			//得到此 Class所表示的直接超类的Type
			System.out.println(getClass().getGenericSuperclass());
			return (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			//接口的泛型信息 
			System.out.println(getClass().getSuperclass()+" , "+getClass().getSuperclass().getGenericInterfaces());
			return (Class<T>) getEntity(getClass().getSuperclass()
					.getGenericInterfaces());
		}
	}

	protected Class<?> getEntity(Type[] types) {
		Class<?> entityClass = null;
		for (Type type : types) {
			System.out.println("type:"+type);
			if (type instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) type)
						.getActualTypeArguments()[0];
			} else if (type instanceof Class) {
				entityClass = getEntity(((Class<?>) type)
						.getGenericInterfaces());
			}
		}
		return entityClass;
	}

}
