package com.wqj.dropLoad.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wqj.common.bean.Page;
import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.dropLoad.entity.BaseLanguage;
import com.wqj.dropLoad.service.BaseLanguageService;

@Transactional(readOnly=true) 
@Service("baseLanguageService")
public class BaseLanguageServiceImpl extends HibernateDaoSupport<BaseLanguage>  implements BaseLanguageService {

	@Override
	public List<BaseLanguage> getLanguageByParam(long id) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		if(id>0){
			detachedCriteria.add(Restrictions.lt("languageId", id));
		}
		//排序
		detachedCriteria.addOrder(Order.desc("languageId")); 
		Page page = new Page();
		return findPage(detachedCriteria,page);
	}
}
