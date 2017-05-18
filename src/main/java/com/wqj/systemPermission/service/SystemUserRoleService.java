package com.wqj.systemPermission.service;

import java.util.List;

import com.wqj.common.orm.dao.BaseDao;
import com.wqj.systemPermission.entity.SystemUserInfo;
import com.wqj.systemPermission.entity.SystemUserRoleInfo;

public interface SystemUserRoleService extends BaseDao<SystemUserRoleInfo> {

	List<SystemUserRoleInfo> getUserRoleSystemUser(SystemUserInfo currentUser);

}
