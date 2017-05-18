package com.wqj.systemPermission.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.systemPermission.entity.DepartmentInfo;
import com.wqj.systemPermission.service.DepartmentService;

@Service
public class DepartmentServiceImpl  extends HibernateDaoSupport<DepartmentInfo> implements DepartmentService{

	@Override
	public List<DepartmentInfo> getRootDepartmentList() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.isNull("parentDepartment"));
		return findDatas(detachedCriteria);
	}

	@Override
	public List<DepartmentInfo> getChildDepartmentList(Long departmentId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("parentDepartment.departmentId", departmentId));
		return findDatas(detachedCriteria);
	}

	@Override
	public void getDepartmentTreeList(List<DepartmentInfo> departmentList, String prefix,
			List<DepartmentInfo> resultList) {
		for (DepartmentInfo department : departmentList) {
			// 根
			department.setDepartmentName(prefix + department.getDepartmentName());
			resultList.add(department);
			// 子树
			getDepartmentTreeList(department.getChilds(), "&nbsp;&nbsp;&nbsp;&nbsp;" + prefix ,resultList);
		}
	}


}
