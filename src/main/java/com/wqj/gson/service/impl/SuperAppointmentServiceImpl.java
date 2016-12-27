package com.wqj.gson.service.impl;

import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.gson.entity.SuperAppointmentInfo;
import com.wqj.gson.service.SuperAppointmentService;

@Service("superAppointmentService")
public class SuperAppointmentServiceImpl extends HibernateDaoSupport<SuperAppointmentInfo> implements SuperAppointmentService{

}
