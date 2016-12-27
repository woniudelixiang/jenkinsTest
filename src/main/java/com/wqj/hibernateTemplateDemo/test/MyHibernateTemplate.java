package com.wqj.hibernateTemplateDemo.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * http://blog.csdn.net/csh624366188/article/details/7665489
 *
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016-2-2 下午3:28:04
 */
public class MyHibernateTemplate {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~MyHibernateTemplate---->>>>> setSessionFactory~~~~~~~~~~~~~~~~~~~~~~~~");
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}
	
	public void executeWithNativeSession(MyHibernateCallback callback) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			callback.doInHibernate(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			// ...
		}
	}

	public void save(final Object o) {
		new MyHibernateTemplate()
				.executeWithNativeSession(new MyHibernateCallback() {
					public void doInHibernate(Session s) {
						s.save(o);
					}
				});
	}
}
