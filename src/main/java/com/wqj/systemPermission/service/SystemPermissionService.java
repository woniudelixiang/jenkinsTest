package com.wqj.systemPermission.service;

import java.util.List;

import com.wqj.common.orm.dao.BaseDao;
import com.wqj.systemPermission.entity.SystemPermissionInfo;

public interface SystemPermissionService extends BaseDao<SystemPermissionInfo> {

	List<SystemPermissionInfo> getRootMenu();

}
