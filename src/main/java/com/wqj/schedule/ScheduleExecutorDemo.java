package com.wqj.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorDemo implements Runnable{

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("任务开始执行时间：  " + sdf.format(new Date()));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
//		pool.scheduleAtFixedRate(new ScheduleExecutorDemo(), 1, 1, TimeUnit.SECONDS);
		pool.scheduleWithFixedDelay(new ScheduleExecutorDemo(), 1, 1, TimeUnit.SECONDS);
	}
	
	
}
