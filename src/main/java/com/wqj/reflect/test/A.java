package com.wqj.reflect.test;

import java.lang.reflect.Method;

public class A {

	public void dealWith(final String tag, final String sbody){
		final Object objec = this;
		try {
			D d = new D();
			d.sys(new C(){

				@Override
				public void message() {
					System.out.println(1);
					System.out.println(objec.getClass().getSimpleName());
					try {
					Class clazz = this.getClass();
					Method method = clazz.getDeclaredMethod(tag, String.class,String.class);
					method.invoke(this, tag,sbody);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
				public void method1(String tag, String sbody){
					System.out.println("********method1********");
				}
				
				public void method2(String tag, String sbody){
					System.out.println("********method2********");
				}
				
			});
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
