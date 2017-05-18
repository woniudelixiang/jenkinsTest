package com.wqj.oop.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// http://www.cnblogs.com/duanxz/p/4489331.html
public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(3);// 创建CyclicBarrier对象并设置3个公共屏障点
      
        System.out.println("创建子线程开始");
        for (int i = 0; i < 5; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(
                                "线程" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有" + (cb.getNumberWaiting() + 1)
                                        + "个已到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        try {
                            cb.await();  //  在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(
                                "线程" + Thread.currentThread().getName() + "即将到达集合地点2，当前已有" + (cb.getNumberWaiting() + 1)
                                        + "个已到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        try {
                            cb.await();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(
                                "线程" + Thread.currentThread().getName() + "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1)
                                        + "个已到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        try {
                            cb.await();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
            System.out.println("子线程 "+ i + "提交到线程池中");
        }
        System.out.println("所有子线程提交完毕，线程池关闭,但线程池关闭的真实时间为所有子线程都执行完毕后");
        service.shutdown(); //线程池的状态则立刻变成SHUTDOWN状态,以后不能再往线程池中添加任何任务，否则将会抛出RejectedExecutionException异常。但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。 与它相似的还有一个shutdownNow()，它通过调用Thread.interrupt来实现线程的立即退出。
    }

   
}
