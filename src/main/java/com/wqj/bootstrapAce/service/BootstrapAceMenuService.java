package com.wqj.bootstrapAce.service;

import java.io.Serializable;
import java.util.List;

import com.wqj.bootstrapAce.entity.BootstrapAceMenu;
import com.wqj.common.orm.dao.BaseDao;

public interface BootstrapAceMenuService extends BaseDao<BootstrapAceMenu>{

	List<BootstrapAceMenu> getRootMenus();
	List<BootstrapAceMenu> loopQueryMenusByParent(List<BootstrapAceMenu> menus);
	List<BootstrapAceMenu> findMenusByParent(Serializable parentId);
	
}
