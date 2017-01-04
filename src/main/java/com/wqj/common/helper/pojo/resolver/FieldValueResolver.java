package com.wqj.common.helper.pojo.resolver;

import java.lang.reflect.Field;

/**
 * https://yq.aliyun.com/articles/38956    //思想来源于SpringMVC参数解析
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年1月4日 下午5:53:26
 */

public interface FieldValueResolver {

	 /**
	  * 解析器是否支持当前参数
	  * @param clazz
	  * @return
	  */
	boolean isSupport(Class<?> clazz);
	
	/**
	 * 将参数解析到当前对象上
	 * @param keyArray
	 * @param obj
	 * @param position
	 * @param value
	 * @param positionKeyIndex
	 * @param field
	 * @param fieldValue
	 * @return
	 * @throws Exception
	 */
	Object fieldValueResolver(String[] keyArray, Object obj, int position, Object value,String positionKeyIndex, Field field, Object fieldValue) throws Exception;
	
}
