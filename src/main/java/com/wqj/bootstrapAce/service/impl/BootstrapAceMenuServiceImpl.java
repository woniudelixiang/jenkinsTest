package com.wqj.bootstrapAce.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wqj.bootstrapAce.entity.BootstrapAceMenu;
import com.wqj.bootstrapAce.service.BootstrapAceMenuService;
import com.wqj.common.orm.dao.HibernateDaoSupport;

@Service("bootstrapAceMenuService")
public class BootstrapAceMenuServiceImpl  extends HibernateDaoSupport<BootstrapAceMenu> implements BootstrapAceMenuService{

	@Override
	public List<BootstrapAceMenu> getRootMenus() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.isNull("parent"));
		List<BootstrapAceMenu> list = findDatas(detachedCriteria);
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<BootstrapAceMenu> loopQueryMenusByParent(List<BootstrapAceMenu> menus) {
		if (menus != null) {
			for (BootstrapAceMenu menu : menus) {
				menu.setChilds(findMenusByParent(menu.getId()));
				loopQueryMenusByParent((List<BootstrapAceMenu>) menu.getChilds());
			}
		}
		return menus;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<BootstrapAceMenu> findMenusByParent(Serializable parentId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		return criteria.list();
	}
	
}
