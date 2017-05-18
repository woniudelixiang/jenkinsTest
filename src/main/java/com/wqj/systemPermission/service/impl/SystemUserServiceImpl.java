package com.wqj.systemPermission.service.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.systemPermission.entity.SystemUserInfo;
import com.wqj.systemPermission.service.SystemUserService;

@Service
public class SystemUserServiceImpl extends HibernateDaoSupport<SystemUserInfo> implements SystemUserService{

	@Override
	public List<SystemUserInfo> getUserList() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return findDatas(detachedCriteria);
	}

	@Override
	public List<SystemUserInfo> findUserByIdentity(SystemUserInfo userInfo) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("userName",userInfo.getUserName()));
		detachedCriteria.add(Restrictions.eq("password",userInfo.getPassword()));
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return findDatas(detachedCriteria);
	}

}
