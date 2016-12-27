package com.wqj.hibernateTemplateDemo.dao;

import com.wqj.hibernateTemplateDemo.test.MyHibernateTemplate;

public class UserDao {
	private MyHibernateTemplate hibernateTemplate;

	public MyHibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(MyHibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
