package com.wqj.oop.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * http://www.cnblogs.com/liuling/archive/2013/08/21/2013-8-21-03.html
 * http://blog.csdn.net/mchenys/article/details/51418826
 * 
 */
public class CacheDemo {
    //缓存Map
    private Map<String, Object> cache = new HashMap<String, Object>();
	    
    //定义读写锁
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    /**
    *  获取数据的方法,先从缓存获取,缓存有则直接返回,否则查询数据库并保存到缓存中再返回结果.
    */
    public  Object getData(String key){
        rwl.readLock().lock();//上读锁
        Object value = null;
        try{
            value = cache.get(key);//先从缓存读取数据
            if(value == null){
                rwl.readLock().unlock();//先进来的那个线程先释放读锁
                rwl.writeLock().lock();//然后再上写锁,后面进来的线程就只能等待
                try{
                    if(value==null){ //这里继续判空,是避免当拿到写锁的线程释放了写锁后,后面等待的线程拿到写锁后继续操作value
                        value = "query db....";//如果数据为空,再执行查询数据库操作
                        cache.put(key,value);//查询到后写入缓存
                    }
                }finally{
                    rwl.writeLock().unlock();//最后释放写锁
                }
                rwl.readLock().lock();//重新恢复读锁
            }
        }finally{
            rwl.readLock().unlock();//最后释放读锁
        }
        return value;//返回结果
    }
}