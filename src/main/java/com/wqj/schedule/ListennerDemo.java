package com.wqj.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class ListennerDemo implements JobListener{

	@Override
	public String getName() {
		return "myListenner";
	}

	//  任务调度之前执行的方法
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		System.out.println(" 任务调度之前执行的方法 ");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println(" 任务调度之前执行的方法 ");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		System.out.println(" 任务执行完毕  ");
		if(jobException!=null){
			System.out.println(" 发送邮件 ");
		}
	}

}
