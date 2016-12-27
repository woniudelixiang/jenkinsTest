package com.wqj.parameBinding.util;

import java.beans.PropertyEditorSupport;
import com.wqj.parameBinding.entity.User;

public class MyPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User user =  new User();
		System.out.println(text);
		if(text.indexOf(",")!=-1){
			String[] textArray = text.split(",");
			user.setName(textArray[0]);
			user.setAge(Integer.parseInt(textArray[1]));
		}
		this.setValue(user);
	}
	
}
