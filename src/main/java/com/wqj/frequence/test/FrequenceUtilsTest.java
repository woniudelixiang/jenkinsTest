package com.wqj.frequence.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.wqj.frequence.util.FrequenceUtils;

public class FrequenceUtilsTest {

//	@Test  
    public void testLimit() throws InterruptedException {  
        FrequenceTestClass ftc = new FrequenceTestClass(100, 1);  
        for(int i = 0; i < 100; i++) {  
           // ftc.method(i);  
        }  
       
        assertTrue(true);  
    }  
    
    @Test  
    public void testLimitMutiThreads() throws InterruptedException {  
        Thread t1 = new Thread(new FrequenceTestClass(1000*6, 1));
        t1.start(); 
        
          
        Thread t2 = new Thread(new FrequenceTestClass(1000, 5));  
        t2.start(); 
  
        Thread.sleep(100000000);
    }
  
    class FrequenceTestClass implements Runnable {  
        final long limitTime;  
        final int limitCount;  
        FrequenceTestClass(final long limitTime, final int limitCount) {  
            this.limitTime = limitTime;  
            this.limitCount = limitCount;  
        } 
        
        public void method(int i) throws Exception {  
            FrequenceUtils.limit(limitTime, limitCount);  
            System.out.println("tid:" + Thread.currentThread().getId() + ", i=" + i);  
        }
  
        @Override  
        public void run() {  
            for(int i = 1; i < 125; i++) {  
                try {
                	if(i%2 == 0){
                		Thread.sleep(6000);
                	}
					method(i);
				} catch (Exception e) {
					e.printStackTrace();
					 try {
						Thread.sleep(6000);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
            }  
        }  
    }  
  
}  
