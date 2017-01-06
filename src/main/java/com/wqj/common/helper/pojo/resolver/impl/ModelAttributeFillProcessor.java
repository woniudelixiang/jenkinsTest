package com.wqj.common.helper.pojo.resolver.impl;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.wqj.common.helper.pojo.ReflectionUtils;
import com.wqj.common.helper.pojo.resolver.FieldValueResolver;

@Service("modelAttributeFillProcessor")
public class ModelAttributeFillProcessor implements FieldValueResolver{

	private boolean annotationNotRequired;

	public ModelAttributeFillProcessor(boolean annotationNotRequired) {
		this.annotationNotRequired = annotationNotRequired;
	}
	
	public ModelAttributeFillProcessor() {
		super();
	}
	
	@Override
	public boolean isSupport(Class<?> clazz) {
		if (this.annotationNotRequired) {
			return true;
		}
		return false;
	}

	@Override
	public Object fieldValueResolver(String[] keyArray, Object obj, int position, Object value, String positionKeyIndex,
			Field field, Object fieldValue) throws Exception {
		
		System.out.println("---------------   catch-all  ---------------------------");
		fieldValue = ReflectionUtils.fillLoop( keyArray, fieldValue,++position,value);
		Object manageValue = ReflectionUtils.convertValue(fieldValue,field.getType()); //
		BeanUtils.setProperty(obj, field.getName(), manageValue);
		return fieldValue;
	}
}
