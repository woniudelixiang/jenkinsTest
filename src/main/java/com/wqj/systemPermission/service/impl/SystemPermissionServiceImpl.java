package com.wqj.systemPermission.service.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.systemPermission.entity.SystemPermissionInfo;
import com.wqj.systemPermission.service.SystemPermissionService;

@Service
public class SystemPermissionServiceImpl extends HibernateDaoSupport<SystemPermissionInfo> implements SystemPermissionService{

	@Override
	public List<SystemPermissionInfo> getRootMenu() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.isNull("parentPermission"));
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return findDatas(detachedCriteria);
	}

}
