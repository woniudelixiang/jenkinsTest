package com.wqj.daoDemo.service.impl;

import org.springframework.stereotype.Service;
import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.daoDemo.entity.Student;
import com.wqj.daoDemo.service.StudentService;

@Service("studentService")
public class StudentServiceImpl extends HibernateDaoSupport<Student> implements StudentService{

	public StudentServiceImpl(){
		System.out.println("*****   StudentServiceImpl  init   *********");
	}
}
