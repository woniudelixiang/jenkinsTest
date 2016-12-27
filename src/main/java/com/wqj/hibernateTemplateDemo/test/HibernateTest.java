package com.wqj.hibernateTemplateDemo.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wqj.daoDemo.entity.User;

public class HibernateTest {

	private static ApplicationContext context;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void test() {
	//	SessionFactoryImpl sessionFactory =  (SessionFactoryImpl) context.getBean("sessionFactory");
		//Session session = sessionFactory.openSession();
		System.out.println("=======================================");
		User user = new User();
		user.setUsername("王启军1");
		user.setPassword("123456");
		
		MyHibernateTemplate myHibernateTemplate = new MyHibernateTemplate();
		//myHibernateTemplate.setSession(session);
		//System.out.println("ssssss:"+myHibernateTemplate.getSession());
		//session.beginTransaction();
		
		myHibernateTemplate.save(user);
	//	System.out.println(sessionFactory);
	}
}
