package com.wqj.frequence.test;
import org.apache.commons.lang.time.StopWatch; 

public class StopWatchTest {
	public static void main(String[] args) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		
		//统计从start开始经历的时间
		Thread.sleep(1000);
		System.out.println(watch.getTime());  // 调用start()方法到调用getTime()或stop()方法耗用的时间 
		
		//统计计时点
		Thread.sleep(1000);
		watch.split();
		System.out.println(watch.getSplitTime());   // 调用start()方法到最后一次调用split()方法耗用的时间
		
		//统计计时点
		Thread.sleep(1000);
		watch.split();
		System.out.println(watch.getSplitTime());
		
		
		//复位后, 重新计时
		watch.reset();
		watch.start();
		Thread.sleep(1000);
		System.out.println(watch.getTime());
		
		//暂停 与 恢复
		watch.suspend();
		System.out.println("暂停2秒钟");
		Thread.sleep(2000);
		
		watch.resume();
		Thread.sleep(1000);
		watch.stop();
		System.out.println(watch.getTime());
	}
}
