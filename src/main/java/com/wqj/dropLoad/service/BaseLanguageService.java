package com.wqj.dropLoad.service;

import java.util.List;

import com.wqj.common.orm.dao.BaseDao;
import com.wqj.dropLoad.entity.BaseLanguage;

public interface BaseLanguageService extends BaseDao<BaseLanguage> {
	
	List<BaseLanguage> getLanguageByParam(long id);
}
