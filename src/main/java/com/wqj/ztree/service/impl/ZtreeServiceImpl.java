package com.wqj.ztree.service.impl;

import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.ztree.entity.ZtreeMenu;
import com.wqj.ztree.service.ZtreeService;

@Service
public class ZtreeServiceImpl extends HibernateDaoSupport<ZtreeMenu> implements ZtreeService{

}
