package com.wqj.common.helper.pojo.resolver.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.wqj.common.helper.pojo.ReflectionUtils;
import com.wqj.common.helper.pojo.resolver.FieldValueResolver;
import com.wqj.common.util.ValidateUtils;

@Service("mapFieldValueResolver")
public class MapFieldValueResolver implements FieldValueResolver{
	
	@Override
	public boolean isSupport(Class<?> clazz) {
		return Map.class.isAssignableFrom(clazz);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object fieldValueResolver(String[] keyArray, Object obj, int position, Object value,String positionKeyIndex, Field field, Object fieldValue) throws Exception {
		System.out.println("================  map处理了  =================");
		Type type = field.getGenericType();
		List<String> listParameterizedTypeStrArray = ReflectionUtils.getListParameterizedTypeStrArray(type);
		Map map = (Map)fieldValue;
		Object oldValue = map.get(positionKeyIndex);
		if(ValidateUtils.isEmpty(oldValue)){
			oldValue = ReflectionUtils.newInstance(listParameterizedTypeStrArray.get(1));
		}
		oldValue = ReflectionUtils.fillLoop( keyArray, oldValue,++position,value);
		map.put(positionKeyIndex, oldValue);
		
		Object convertValue = ReflectionUtils.convertValue(fieldValue,field.getType());
		BeanUtils.setProperty(obj, field.getName(), convertValue); 
		return null;
	}
	
}
