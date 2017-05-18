package com.wqj.oop.thread.pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import javassist.bytecode.analysis.Executor;

public class ThreadPoolTest {

	public static void main(String[] args) {
		// 固定线程池
//		ExecutorService threadPool =  Executors.newFixedThreadPool(5);
		// 线程池中的 线程数动态变化（缓存线程池），随着任务的增加而增加
		ExecutorService threadPool =  Executors.newCachedThreadPool();
		// 单一线程池（当线程死掉后重新启动一个，保证池子中有一个线程）
//		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
		
		// 定时器线程池
//		ScheduledFuture<?> schedule = Executors.newScheduledThreadPool(3)
//				.schedule(new Runnable() {
//					@Override
//					public void run() {
//						System.out.println("=============");
//					}
//				} , 10, TimeUnit.SECONDS);
		
		
		for (int i = 0; i < 10; i++) {
			 final int task  = i +1;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10; i++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " , " + i + " , 第" + task + " 次任务");
					}
				}
			});
		}
		// 关闭线程池中的线程
		threadPool.shutdown();
		
	}
	
	@Test
	public void testCallableAndFuture() throws InterruptedException, ExecutionException{
		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(
				new Callable<String>() {
					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						return "Hello";
					}
				});
		// 获取任务的结果
		System.out.println(future.get());
	}
	
	@Test
	public void testCompletionService() throws Exception{
		ExecutorService threadPool =  Executors.newFixedThreadPool(5);
		// 从threadPool池子中执行任务，任务返回整数
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
		
		// 添加任务
		for (int i = 0; i < 10; i++) {
			final int task = i+1;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					Thread.sleep(new Random().nextInt(5000));
					return task;
				}
			});
		}
		
		for (int i = 0; i < 10; i++) {
			Integer result = completionService.take().get();
			System.out.println(result);
		}
		
	}
	
}
