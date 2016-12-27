package com.wqj.gson.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016-3-8 下午6:17:30
 */
public class FilterInclusionStrategy implements ExclusionStrategy{
	//需要序列化的属性.   
	private String[] includeFields;  
	//需要序列化的类.  
    private Class<?>[] includeClasses;  
    //相反
    private boolean reverse = false;
    
    public FilterInclusionStrategy() {}

	public FilterInclusionStrategy(String[] includeFields,
			Class<?>[] includeClasses) {
		this.includeFields = includeFields;
		this.includeClasses = includeClasses;
	}
    
    public FilterInclusionStrategy(String[] includeFields,
			Class<?>[] includeClasses, boolean reverse) {
		this.includeFields = includeFields;
		this.includeClasses = includeClasses;
		this.reverse = reverse;
	}
    
	@Override
    public boolean shouldSkipClass(Class<?> clazz) {  
		  if(this.includeClasses != null){
			  for (Class<?> includeClass : includeClasses) {  
		      	if (includeClass.getName().equals(clazz.getName())) { 
		              return reverse;
		          }  
		      }  
		  }
        return false;
    }
  
    @Override
    public boolean shouldSkipField(FieldAttributes f) {  
        if(this.includeFields!=null){
        	 for (String field : this.includeFields) {  
                 if (field.equals(f.getName())) {  
                     return reverse;  
                 }  
             }  
        }
        return !reverse;
    }

	public void setFields(String[] includeFields) {
		this.includeFields = includeFields;
	}

	public void setClasses(Class<?>[] includeClasses) {
		this.includeClasses = includeClasses;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
}
