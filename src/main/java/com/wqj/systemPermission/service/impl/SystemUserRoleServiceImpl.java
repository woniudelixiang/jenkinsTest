package com.wqj.systemPermission.service.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.systemPermission.entity.SystemUserInfo;
import com.wqj.systemPermission.entity.SystemUserRoleInfo;
import com.wqj.systemPermission.service.SystemUserRoleService;

@Service
public class SystemUserRoleServiceImpl extends HibernateDaoSupport<SystemUserRoleInfo> implements SystemUserRoleService{

	@Override
	public List<SystemUserRoleInfo> getUserRoleSystemUser(SystemUserInfo currentUser) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("systemUser", currentUser));
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return findDatas(detachedCriteria);
	}

}
