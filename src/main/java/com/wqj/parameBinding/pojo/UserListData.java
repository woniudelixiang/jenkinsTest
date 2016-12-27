package com.wqj.parameBinding.pojo;

import java.util.List;
import com.wqj.parameBinding.entity.User;

public class UserListData {
	private List<User> userList;

	
	@Override
	public String toString() {
		return "UserListData [userList=" + userList + "]";
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
