package com.wqj.menuStudy.service;

import java.io.Serializable;
import java.util.List;
import com.wqj.common.orm.dao.BaseDao;
import com.wqj.menuStudy.entity.Menu;

public interface TestMenuService extends BaseDao<Menu> {
	List<Menu> getRootMenus();
	List<Menu> loopQueryMenusByParent(List<Menu> menus);
	List<Menu> findMenusByParent(Serializable parentId);
	List<Menu> handle(long menuId, List<Menu> menus);
}
