package com.wqj.validator.pojo;

import com.wqj.validator.validatorExtend.annotation.Matches;

@Matches(field = "password", verifyField = "confirmPassword",  message = "{password.verify}")  
public class VerifyBean {
	private String password;  
    private String confirmPassword;
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
