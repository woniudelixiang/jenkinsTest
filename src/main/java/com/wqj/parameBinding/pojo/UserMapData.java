package com.wqj.parameBinding.pojo;

import java.util.Map;
import com.wqj.parameBinding.entity.User;

public class UserMapData {
	private Map<String, User> userMap;
	
	@Override
	public String toString() {
		return "UserMapData [userMap=" + userMap + "]";
	}

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}
}
