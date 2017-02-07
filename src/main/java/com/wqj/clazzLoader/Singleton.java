package com.wqj.clazzLoader;

/**
 * 百度搜索 : Java SE 第一百二十五,六....讲
 * http://edu.51cto.com/lesson/id-36891.html
 * http://v.youku.com/v_show/id_XNDcxMzAyNzk2.html  视频教程
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年1月12日 上午9:39:06
 */
public class Singleton {
	
	private static Singleton singleton = new Singleton();

	public static int count1;
	public static int count2 = 0;
	
//	private static Singleton singleton = new Singleton();
	
	private Singleton(){
		count1++;
		count2++;
	}
	
	public static Singleton getInstance(){
		return singleton;
	}
}
