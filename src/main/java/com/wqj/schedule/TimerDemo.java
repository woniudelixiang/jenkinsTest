package com.wqj.schedule;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo extends TimerTask{

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("任务开始执行时间：  " + sdf.format(this.scheduledExecutionTime()));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		// 每次执行时间为上一次任务结束起向后推一个时间间隔
		timer.schedule(new TimerDemo(), 1000, 1000); // 从现在开始1秒之后每隔1秒执行一次
		
		// 每次执行时间为上一次任务开始起向后推一个时间间隔
//		timer.scheduleAtFixedRate(new TimerDemo(), 1*1000, 1*1000);
	}
	
}
