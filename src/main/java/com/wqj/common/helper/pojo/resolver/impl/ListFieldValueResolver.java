package com.wqj.common.helper.pojo.resolver.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.wqj.common.helper.pojo.ReflectionUtils;
import com.wqj.common.helper.pojo.resolver.FieldValueResolver;

@Service("listFieldValueResolver")
public class ListFieldValueResolver implements FieldValueResolver{

	private static int AUTOGROWCOLLECTIONLIMIT = Integer.MAX_VALUE;

	@Override
	public boolean isSupport(Class<?> clazz) {
		return List.class.isAssignableFrom(clazz);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object fieldValueResolver(String[] keyArray, Object obj, int position, Object value,String positionKeyIndex, Field field, Object fieldValue) throws Exception {
		System.out.println("================  list处理了  =================");
		int index = Integer.parseInt(positionKeyIndex);

		Type type = field.getGenericType();
		List<String> listParameterizedTypeStrArray = ReflectionUtils.getListParameterizedTypeStrArray(type);

		List list = (List)fieldValue;

		int size = list.size();
		Object oldValue = null;
		if ( index < size) {
			oldValue = list.get(index);
		}

		if(oldValue == null){
			oldValue = ReflectionUtils.newInstance(listParameterizedTypeStrArray.get(0));
		}

		oldValue = ReflectionUtils.fillLoop( keyArray, oldValue,++position, value);
		System.out.println("oldValue: " + oldValue);
		
		if (index >= size && index < AUTOGROWCOLLECTIONLIMIT) {
			for (int i = size; i < index; i++) {
				list.add(null);
			}
			list.add(oldValue);
		}else {
			list.set(index, oldValue);
		}
		
		Object convertValue = ReflectionUtils.convertValue(fieldValue,field.getType()); //
		BeanUtils.setProperty(obj, field.getName(), convertValue); //
		return null;
	}

}
