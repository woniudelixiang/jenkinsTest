package com.wqj.parameBinding.pojo;

import java.util.Set;
import com.wqj.parameBinding.entity.User;
import jersey.repackaged.com.google.common.collect.Sets;

public class UserSetData {
	private Set<User> userSet = Sets.newHashSet();

	public UserSetData() {
		userSet.add(new User());
		userSet.add(new User());
		userSet.add(new User());
	}

	@Override
	public String toString() {
		return "UserSetData [userSet=" + userSet + "]";
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
}
