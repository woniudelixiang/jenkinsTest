package com.wqj.oop.thread.syn.communication;


/**
 * 子线程循环10次，接着主线程循环20次，
 * 接着又回到子线程循环10次，接着再回到主线程循环20次，
 * 如此循环5次，请写出程序代码
 */
public class Test1 {

	
//	public static void main(String[] args) {
//		
//		// 子线程
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int j = 0; j < 5; j++) {
//					for (int i = 0; i < 10; i++) {
//						System.out.println("子线程，第 " + (j+1) + " 轮，第 " + (i+1) +" 次打印");
//					}
//				}
//			}
//		}).start();
//		
//		// 主线程
//		for (int j = 0; j < 5; j++) {
//			for (int i = 0; i < 20; i++) {
//				System.out.println("========================>>>>  主线程，第 " + (j+1) + " 轮，第 " + (i+1) +" 次打印");
//			}
//		}
//		
//		
//	}
	
	
	
	public static void main(String[] args){
		final Business bus = new Business();
		
		// 子线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j = 0; j < 5; j++) {
					bus.sub(j);
				}
			}
		}).start();
		
		// 主线程
		for (int j = 0; j < 5; j++) {
			bus.main(j);
		}
	}
}




