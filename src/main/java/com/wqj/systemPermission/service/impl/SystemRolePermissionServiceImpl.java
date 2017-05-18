package com.wqj.systemPermission.service.impl;

import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.systemPermission.entity.SystemRolePermissionInfo;
import com.wqj.systemPermission.service.SystemRolePermissionService;

@Service
public class SystemRolePermissionServiceImpl extends HibernateDaoSupport<SystemRolePermissionInfo> implements SystemRolePermissionService{

}
