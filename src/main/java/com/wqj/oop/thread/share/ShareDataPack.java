package com.wqj.oop.thread.share;

public class ShareDataPack {
	private String name;
	private int age;
	
//	private ShareDataPack(){
//		
//	}
	
	private static ThreadLocal<ShareDataPack> localMap = new ThreadLocal<ShareDataPack>();
	
	public static ShareDataPack getThreadInstance(){
		ShareDataPack instance = localMap.get();
		if(instance == null){
			instance = new ShareDataPack();
			localMap.set(instance);
		}
		return instance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShareData [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}
}
