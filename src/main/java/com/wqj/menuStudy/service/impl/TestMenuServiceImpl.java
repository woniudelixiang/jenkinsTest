package com.wqj.menuStudy.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.menuStudy.entity.Menu;
import com.wqj.menuStudy.service.TestMenuService;

@Service
public class TestMenuServiceImpl extends HibernateDaoSupport<Menu> implements TestMenuService{

	@Override
	public List<Menu> getRootMenus() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.isNull("parent"));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		List<Menu> list = findDatas(detachedCriteria);
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> loopQueryMenusByParent(List<Menu> menus) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (BooleanUtils.isTrue(menu.getExpanded())) {
					menu.setChild(findMenusByParent(menu.getId()));
					loopQueryMenusByParent((List<Menu>) menu.getChild());
				}
			}
		}
		return menus;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> findMenusByParent(Serializable parentId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> handle(long menuId, List<Menu> menus) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (menu.getId() == menuId) {
					menu.setExpanded(!BooleanUtils.isTrue(menu.getExpanded()));
					if (BooleanUtils.isTrue(menu.getExpanded())
							&& CollectionUtils.isEmpty(menu.getChild())) {
						menu.setChild(findMenusByParent(menu.getId()));
						loopQueryMenusByParent((List<Menu>) menu.getChild());
					}
					return menus;
				} else {
					handle(menuId, (List<Menu>) menu.getChild());
				}
			}
		}
		return menus;
	}
	
}
