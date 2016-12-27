package com.wqj.daoDemo;

import java.lang.reflect.Modifier;

import com.google.gson.GsonBuilder;
import com.wqj.common.util.JSONHelper;
import com.wqj.daoDemo.entity.User;

public class Test {

	/*public static void main(String[] args) {
		outer:for(int i=0;i<4;i++){
			for(int j=0;j<3;j++){
				if(i*j == 2){
					break outer;
				}
				System.out.println(i+" , "+j);
			}
		}
	}*/
	
	
	public static void main(String[] args) {
		
		User user = new User();
		user.setUsername("启军");
		//System.out.println(new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create().toJson(user));
		//System.out.println(new GsonBuilder().);
		System.out.println(JSONHelper.toJson(user));
		
	}
	
	
}
