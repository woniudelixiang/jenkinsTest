package com.wqj.parameBinding.pojo;

import java.util.Map;

public class LevelMapData {
	Map<String,String> userMap;

	public Map<String, String> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, String> userMap) {
		this.userMap = userMap;
	}

	@Override
	public String toString() {
		return "LevelMapData [userMap=" + userMap + "]";
	}
	
}
