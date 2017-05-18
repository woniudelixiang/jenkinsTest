package com.wqj.systemPermission.service;

import java.util.List;

import com.wqj.common.orm.dao.BaseDao;
import com.wqj.systemPermission.entity.SystemUserInfo;

public interface SystemUserService extends BaseDao<SystemUserInfo> {

	List<SystemUserInfo> getUserList();

	List<SystemUserInfo> findUserByIdentity(SystemUserInfo userInfo);

}
