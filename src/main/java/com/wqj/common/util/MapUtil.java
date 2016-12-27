package com.wqj.common.util;

import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {

	public static void printMap(Map<String, Object> map){
		for (Entry<String, Object> entry : map.entrySet()) {
			String propertyName = entry.getKey();
			Object value = entry.getValue();
			System.out.println("propertyName: "+propertyName+"  value:"+value);
		}
	}
}
