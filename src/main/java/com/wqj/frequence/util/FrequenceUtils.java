package com.wqj.frequence.util;

import org.apache.commons.lang.time.StopWatch;  

public class FrequenceUtils {
	
	public static void limit(final long limitSplitTime, final int limitCount) throws Exception {
		
		FrequenceUnit funit = threadLocal.get();
		funit.limitSplitTime = limitSplitTime;
		funit.limitCount = limitCount;
		funit.watch.split();    // 统计计时点
		long diffTime = funit.limitSplitTime - funit.watch.getSplitTime();

		System.out.println("diffTime : " + diffTime);
		if (diffTime >= 0) {  // 表示在重新计算的时间段类
			if (funit.realCount >= funit.limitCount) {
				throw new Exception("访问频率太高。。。。");
			}
		}else{  // 表示不重新计算的时间段类--->>> 重新设置计时
			System.out.println("其他计时段");
			funit.watch.reset();  //复位后
			funit.watch.start();  // 重新计时
			funit.realCount = 0;
		}
		
		funit.realCount++;
	}
	
	
	
 
    private static class FrequenceUnit {          
        FrequenceUnit() {  
            this.watch = new StopWatch();  
        } 
        
        long limitSplitTime;  
        int limitCount;  
        StopWatch watch;          
        int realCount = 0;  
    }  
  
    private static ThreadLocal<FrequenceUnit> threadLocal = new ThreadLocal<FrequenceUnit>(){  
        protected synchronized FrequenceUnit initialValue() {  
            FrequenceUnit funit = new FrequenceUnit();
            funit.watch.start();
            return funit;
        }  
    };  
  
}  
