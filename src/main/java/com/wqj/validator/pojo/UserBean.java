package com.wqj.validator.pojo;

import org.hibernate.validator.constraints.NotEmpty;
import com.wqj.validator.group.UserBeanGroup1;
import com.wqj.validator.group.UserBeanGroup2;

public class UserBean {
	
	@NotEmpty(message="用户名不能为空", groups={UserBeanGroup1.class})  
	private String username;

	@NotEmpty(message="密码不能为空", groups={UserBeanGroup2.class})  
	private String password;
	
	@NotEmpty(message="性别不能为空", groups={UserBeanGroup1.class})  
	private String sex;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
