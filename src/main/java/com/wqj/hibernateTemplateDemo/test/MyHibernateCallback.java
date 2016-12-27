package com.wqj.hibernateTemplateDemo.test;

import org.hibernate.Session;

public interface MyHibernateCallback {
	void doInHibernate(Session s);
}
