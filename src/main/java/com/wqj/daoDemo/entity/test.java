package com.wqj.daoDemo.entity;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.Subcriteria;

public class test {

	private boolean existAlias(Criteria c, String path, String alias) {
		Iterator itm = ((CriteriaImpl) c).iterateSubcriteria();
		while (itm.hasNext()) {
			Subcriteria sub = (Subcriteria) itm.next();
			if (alias.equals(sub.getAlias()) || path.equals(sub.getPath())) {
				return true;
			}
		}
		return false;
	}

	private boolean existAlias(DetachedCriteria c, String path, String alias) {
		Class clazz = c.getClass();
		try {
			Field field = clazz.getDeclaredField("criteria");
			field.setAccessible(true);
			CriteriaImpl ci;
			ci = (CriteriaImpl) field.get(c);
			return existAlias(ci, path, alias);

		} catch (IllegalArgumentException e) {
			//log.error("别名比较出错", e);
		} catch (IllegalAccessException e) {
			//log.error("别名比较出错", e);
		} catch (SecurityException e) {
			//log.error("别名比较出错", e);
		} catch (NoSuchFieldException e) {
			//log.error("别名比较出错", e);
		}

		return false;
	}
}
