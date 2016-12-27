package com.wqj.proxy;

import java.lang.reflect.InvocationHandler;

public class ZhangSan implements People{

	@Override
	public void eat() throws Exception {
		System.out.println("吃饭喜欢看电影");
		// InvocationHandler
	}
	
	@Override
	public void sleep() throws Exception {
		System.out.println("睡觉的时候喜欢磨牙");	
	}
	
	@Override
	public void dadoudou() throws Exception {
		System.out.println("喜欢喝美女一起打豆豆");	
	}

}
