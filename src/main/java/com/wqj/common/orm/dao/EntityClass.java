package com.wqj.common.orm.dao;

import com.wqj.common.orm.entity.BaseEntity;


public class EntityClass extends SuperEntity<BaseEntity>{
	
	
	public static void main(String[] args) {
		EntityClass ec = new EntityClass();
		System.out.println(ec.getEntityClass());
	}
}
