package com.wqj.systemPermission.service;

import java.util.List;

import com.wqj.common.orm.dao.BaseDao;
import com.wqj.systemPermission.entity.SystemRoleInfo;
import com.wqj.systemPermission.entity.SystemUserInfo;
import com.wqj.systemPermission.entity.SystemUserRoleInfo;

public interface SystemRoleService extends BaseDao<SystemRoleInfo> {

	List<SystemRoleInfo> getRootRoleList();

	List<SystemRoleInfo> getRoleTreeList(List<SystemRoleInfo> rootRoleList, String prefix, List<SystemRoleInfo> resultList);

	List<SystemRoleInfo> getChildRoleList(Long roleId);


}
