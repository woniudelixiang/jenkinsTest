package com.wqj.oop.thread;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;  
   
public class Tests extends Thread {  
   
    /** 
     * 3： 现有程序同时启动了四个线程去调用TestDo.doSome(key,value)方法； 
     * 由于TestsDo.doSome(key,value)方法内的代码是先暂停1秒，然后再输出以秒为单位的当前时间值， 
     * 所以会打印出四个相同的时间值，如下所示：
						      4：4 1258199615 
						      1:1 1258199615 
						      3:3 1258199615 
						      2:2 1258199615
     * 请修改代码，如果有几个线程调用TestDo.doSome(key,value)方法时； 
     * 传递进去的key值相等（equals比较为true），则这几个线程应互斥输出结果，即当有两个线程的key都为1时， 
     * 它们中的一个要比其他线程晚一步输出结果，如下所示：
						      4：4 1258199615 
						      1:1 1258199615 
						      3:3 1258199615 
						      1:2 1258199616
     * 总之每个线程中指定的key相等时；这些相等的线程应每隔1秒输出时间值（要用互斥）， key不同，则并行执行（相互之间不互斥） 
     */  
    private TestsDo testDo;  
    private String key;  
    private String value;  
   
    private Tests(String key, String key2, String value) {  
        this.testDo = TestsDo.getInstance();  
        /* 
         * 常量“1”和“1”是同一个对象，下面这行代码就是要用“1”+“”的方式产生新的对象； 
         * 以实现内容没有改变，仍然相等（都还为“1”），但对象却不再是同一个的效果 
         */  
        this.key = key + key2;  // 每一次执行串接操作都会导致新对象的产生
        /* 
         * a = "1"+""; 
         * b = "1"+""; 
         * a和b是同一个对象，因为编译器在执行之前就会将其优化为 a=“1”； 
         * 但是this.key = key + key2;这句，编译器不会给你优化， 
         * 因为你是属性变量，编译器不知道你将来要传入什么值 
         */  
        this.value = value;
    }
   
    public static void main(String[] args) {  
        Tests a = new Tests("1", "", "1");  
        Tests b = new Tests("1", "", "2");  
        Tests c = new Tests("3", "", "3");  
        Tests d = new Tests("4", "", "4");  
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();  
        b.start();  
        c.start();  
        d.start();  
    }  
   
    public void run() {  
        testDo.doSome(key, value);  
    }  
}  
   
class TestsDo { 
    private TestsDo() {}  
    private static TestsDo _instance = new TestsDo();  
    public static TestsDo getInstance() {
        return _instance;  
    }
    
    //传统写法，没有考虑到线程并发问题    
//  private ArrayList keys = new ArrayList();  
    private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();  
    public void doSome(Object key,String value){
        Object o = key;  
        if(! keys.contains(o)){  // o不在list里面就添加
            keys.add(o);  
        }else{  
            //迭代的过程中不能进行其他操作；  
            for(Iterator iter = keys.iterator();iter.hasNext();){ 
                /*这里的休眠作用：为了让大家看到，使用传统的private ArrayList keys = new ArrayList()； 
                 * 会导致Exception in thread "Thread-1" java.util.ConcurrentModificationException异常 
                 * 因为迭代的过程中不能进行其他操作；你非要在迭代的时候向其中添加数据就会导致这种异常，而且在迭代中放入休眠这种错误百发百中。 
                 */  
                try {  
//                    Thread.sleep(20);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                Object oo = iter.next();  
                if(o.equals(oo)){ 
                o = oo;   
                }  
            } 
        }  
        
        //这里为了区别是不同对象，所以不能直接使用synchronized(key)  
        synchronized(o)
        //大括号内的是需要同步的代码，不能改动  
        {  
            try{  
                Thread.sleep(1000);  
                System.out.println(key+":"+value+":" + (System.currentTimeMillis() / 1000));  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
    }
    
   
}
