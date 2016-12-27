package com.wqj.jqueryFileUpload.service.impl;

import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.jqueryFileUpload.entity.BaseImageEntity;
import com.wqj.jqueryFileUpload.service.BaseImageEntityService;

@Service
public class BaseImageEntityServiceImpl extends HibernateDaoSupport<BaseImageEntity> implements BaseImageEntityService{

}
