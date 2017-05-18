package com.wqj.systemPermission.service;

import java.util.List;

import com.wqj.common.orm.dao.BaseDao;
import com.wqj.systemPermission.entity.DepartmentInfo;

public interface DepartmentService  extends BaseDao<DepartmentInfo> {

	List<DepartmentInfo> getRootDepartmentList();

	List<DepartmentInfo> getChildDepartmentList(Long departmentId);

	void getDepartmentTreeList(List<DepartmentInfo> departmentList, String prefix, List<DepartmentInfo> resultList);

}
