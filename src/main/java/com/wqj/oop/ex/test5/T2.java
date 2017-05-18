package com.wqj.oop.ex.test5;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "rawtypes", "unused" })
public class T2 extends T1 {
	private List list = null;
	public MyList mList = new MyList();

	static {
		System.out.println(" static T2 1");
	}

	{
		System.out.println(" T2 1");
	}

	private List t2List = new MyList("t2 list");
	{
		System.out.println(" T2 2");
	}

	static{
		System.out.println("static T2 2");
	}

	public T2(){
		super();
		System.out.println(" construct T2 ");
	}

	@Override
	public void t1() {
		list = new ArrayList();
		System.out.println(" this " + this);
	}

	
	
	/**
	 * 解题方法： jvm加载类的过程        ： 
	 * （1）先加载父类，然后在方法区创建父类的静态变量内存，然后加载子类并创建子类的静态变量内存     
	 * (2)初始化静态变量（依次执行初始化语句块）
	 * @param args
	 */
	public static void main(String[] args) {
		T2 t2 = new T2();
		System.out.println(t2.list);
		System.out.println(" t2 " + t2);
	}

}
