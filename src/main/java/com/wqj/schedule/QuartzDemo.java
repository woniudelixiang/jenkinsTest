package com.wqj.schedule;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.helpers.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Object object = context.getMergedJobDataMap().get("name");
		System.out.println("==>>> " + object);
		throw new RuntimeException("任务调度发生异常了............");
	}
	
	public static void main(String[] args) {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
		
			// JobDetail
			JobDetail jobDetail = new JobDetail("firstJob", "myJobGroup", QuartzDemo.class);
			jobDetail.getJobDataMap().put("name", "wqj");
			jobDetail.addJobListener("myListenner");
			
			// Trigger
//			Trigger trigger = TriggerUtils.makeWeeklyTrigger(2, 16, 05);
//			trigger.setGroup("myTriggerGroup");
//			trigger.setName("myTrigger");
//			trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
			
			CronTrigger trigger = new CronTrigger();
			trigger.setGroup("myTriggerGroup");
			trigger.setName("myTrigger");
			trigger.setCronExpression("1/1 * * * * ?");
			
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.addJobListener(new ListennerDemo());
			scheduler.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
