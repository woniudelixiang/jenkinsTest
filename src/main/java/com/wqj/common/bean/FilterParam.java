package com.wqj.common.bean;

import java.util.Map;

import com.wqj.common.util.JSONHelper;

public class FilterParam {

	Map<String,Object> filterParamMap;
	
	public Map<String, Object> getFilterParamMap() {
		return filterParamMap;
	}
	
	public void setFilterParamMap(Map<String, Object> filterParamMap) {
		this.filterParamMap = filterParamMap;
	}

	@Override
	public String toString() {
		return "查询条件；  " + JSONHelper.toJson(filterParamMap);
	}
}
