package com.wqj.testfinally;

public class TestFinally {
	
	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		try {
			System.out.println("执行try");
			System.exit(-1);
		}catch(Exception e){
			System.out.println("执行catch");    // 1
		} finally {
			System.out.println("执行finally"); //   2
		}
	}
	
}
