package com.wqj.oop.ex.test4;

public class Test4 {

	public static void main(String[] args){
		/**
		 * 解题的知识点： 成员变量是没有动态绑定的 ，成员方法有动态绑定
		 * 成员变量支持重写，但不支持后绑定。即成员变量的调用与调用它们的引用类型严格统一。其中，在没有对成员变量进行重写时，子类引用也使用从父类继承而来的成员变量，
		 * System.out.println(a.count);  a.count  a声明的是ClassA类型，ClassA类型的count为2,输出为2 
		 * a.display();调用方法，a的实际类型是ClassB，通过动态绑定调用ClassB的display方法，输出当前对象的count，为20
		 */
//		ClassA a = new ClassB();
//		System.out.println(a.count);
//		a.display();
		
//		ClassB b = new ClassB();
//		System.out.println(b.count);
//		b.display();
		
		/**
		 * 解题的知识点：  http://blog.csdn.net/gxzzxj/article/details/51946144
		 * 
		 * java中的this指的是实例本身
			在main当中的Father s = new Son();这一行，new Son()是创建了一个Son类的实例，因此，Son类的构造函数中使用this，指的就是这个Son类实例自身，就算是son的构造函数中调用了super，在父类的构造函数中，this也是指向Son类实例。
			而this.a这句，就要看是在父类还是子类了，在父类中就引用父类的成员a，在子成员变量支持重写，但不支持后绑定。即成员变量的调用与调用它们的引用类型严格统一。其中，在没有对成员变量进行重写时，子类引用也使用从父类继承而来的成员变量，
			在JAVA的继承机制中，如果以父类的引用访问子类的对象。
			那么对于被子类重定义的成员变量来说，父类访问的就是自己的成员变量。也就是说，成员变量，是根据引用的类型来进行访问。而方法则不同，父类引用如果调用一个被子类重写的方法，那么其访问的则是子类的方法。也就是说，成员方法，是根据对象的类型进行访问的。

			多态出现的缘由：java的引用变量有两种类型：一个是编译时的类型，一个是运行时类型。
			                               编译时类型：声明该变量时使用的类型决定。
			                               运行时类型：实际赋给该变量的对象决定
			                               如果编译时和运行时类型不一样就会出现所谓的多态。
			 多态：相同类项的变量执行同一个方法时，呈现出不同的行为特征这就是多态。
			注意：引用变量在编译阶段只能调用其编译时类项所具有的方法，但运行时则执行运行时类项所具有的方法，
		 * 
		 * 
		 * 
		 * 
		 */
		Father s = new Son();
		System.out.println(s.a);
		
	}
}
