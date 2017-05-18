package com.wqj.oop.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;  
import java.util.concurrent.SynchronousQueue;  
   
/** 
 * 2： 现成程序中的Test类中的代码在不断地产生数据，然后交给TestDo.doSome()方法去处理； 
 * 这就好像是生产者在不断地产生数据，消费者在不断地消费数据。请将程序改造成有10个线程来消费生产者产生的数据， 
 * 这些消费者都调用TestDo.doSome()方法去处理，固每个消费者都需要1秒才能处理完，程序应该保证这些 
 * 消费者线程依次有序的消费数据，只有上一个消费者消费完后，下一个消费者才能消费数据，下一个消费者是谁都可以， 但要保证消费者拿到的数据是有顺序的。 
 */  
public class Test {  
   
    public static void main(String[] args) { 
        //使用semaphore信号灯相当于上一个lock锁  
        final Semaphore semaphore = new Semaphore(1);
        //新的队列方式  
//        final SynchronousQueue<String> queue = new SynchronousQueue<String>();  
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);  
        for(int i=0;i<10;i++){  
            new Thread(new Runnable() {
                @Override  
                public void run() {
                    try {  
                        semaphore.acquire();
                        String input = queue.take();  
                        String output = TestDo.doSome(input);  
                        System.out.println(Thread.currentThread().getName() + ":" + output);  
                        semaphore.release();
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }).start();  
        }
        
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));  
        
        for (int i = 0; i < 10; i++) { // 这行代码不能改动  
            String input = i + ""; // 这行代码不能改动  
            try {  
                queue.put(input);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    
}  
   
// TestDo类不能动  
class TestDo {
   
    public static String doSome(String input) { 
        try {  
            Thread.sleep(1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        String output = input + ":" + (System.currentTimeMillis() / 1000);  
        return output;  
    }  
   
}