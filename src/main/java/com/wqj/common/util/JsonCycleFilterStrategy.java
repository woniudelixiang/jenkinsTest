package com.wqj.common.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.wqj.common.annotation.JsonCycleFilter;

/** 
 * 类说明 
 * @author George 
 * @date 2015-1-24  
 */
public class JsonCycleFilterStrategy implements ExclusionStrategy {
    
    public boolean shouldSkipClass(Class<?> clazz) {  
    	 return clazz.getAnnotation(JsonCycleFilter.class) != null;
    }

    public boolean shouldSkipField(FieldAttributes f) {
    	 return f.getAnnotation(JsonCycleFilter.class) != null;
    }
}  