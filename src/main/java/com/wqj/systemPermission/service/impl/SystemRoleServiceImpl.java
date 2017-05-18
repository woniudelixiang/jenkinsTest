package com.wqj.systemPermission.service.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.systemPermission.entity.SystemRoleInfo;
import com.wqj.systemPermission.service.SystemRoleService;

@Service
public class SystemRoleServiceImpl  extends HibernateDaoSupport<SystemRoleInfo> implements SystemRoleService{

	@Override
	public List<SystemRoleInfo> getRootRoleList() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.isNull("parentRole"));
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return findDatas(detachedCriteria);
	}

	@Override
	public List<SystemRoleInfo> getRoleTreeList(List<SystemRoleInfo> rootRoleList, String prefix ,List<SystemRoleInfo> resultList) {
		for (SystemRoleInfo systemRoleInfo : rootRoleList) {
			// 根
			systemRoleInfo.setRoleName(prefix + systemRoleInfo.getRoleName());
			resultList.add(systemRoleInfo);
			// 子树
			getRoleTreeList(systemRoleInfo.getChilds(), "&nbsp;&nbsp;&nbsp;&nbsp;" + prefix ,resultList);
		}
		return resultList;
	}

	@Override
	public List<SystemRoleInfo> getChildRoleList(Long roleId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("parentRole.roleId", roleId));
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return findDatas(detachedCriteria);
	}
	
}
