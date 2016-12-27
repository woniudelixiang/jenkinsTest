package com.wqj.lottery.service.impl;

import org.springframework.stereotype.Service;
import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.lottery.entity.Prize;
import com.wqj.lottery.service.PrizeService;

@Service
public class PrizeServiceImpl extends HibernateDaoSupport<Prize> implements PrizeService{

}
