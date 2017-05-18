package com.wqj.systemPermission.install;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.wqj.systemPermission.entity.SystemPermissionInfo;
import com.wqj.systemPermission.entity.SystemUserInfo;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class Installer {

	@Autowired
	private SessionFactory sessionFactory;
	
	Session session ;
	@Test
	public void installer(){
		session = sessionFactory.getCurrentSession();
		
		saveSuperAdmin();
		
		saveSystemPermission();
	}
	
	// 保存超级管理员
	public void saveSuperAdmin(){
		SystemUserInfo user = new SystemUserInfo();
		user.setUserName("super admin");
		user.setPassword("123");
		session.save(user);
	}
	
	// 保存权限
	public void saveSystemPermission(){
		
		SystemPermissionInfo menu, menu1,menu2,menu3,menu4,menu5,menu6,menu7;
		
		menu = new SystemPermissionInfo("syst manager","url","系统管理",null);
		menu1 = new SystemPermissionInfo("post manager","url","岗位管理",menu);
		menu2 = new SystemPermissionInfo("depa manager","url","部门管理",menu);
		menu3 = new SystemPermissionInfo("user manager","url","用户管理",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		
		// 岗位的 增删改查 权限设置
		session.save(new SystemPermissionInfo("post add","sys/role/addRole","岗位_增加",menu1));
		session.save(new SystemPermissionInfo("post del","sys/role/delRole","岗位_删除",menu1));
		session.save(new SystemPermissionInfo("post edit","sys/role/editRole","岗位_修改",menu1));
		session.save(new SystemPermissionInfo("post list","sys/role/list","岗位_列表",menu1));
		
		// 部门的 增删改查 权限设置
		session.save(new SystemPermissionInfo("depa add","url","部门_增加",menu2));
		session.save(new SystemPermissionInfo("depa del","url","部门_删除",menu2));
		session.save(new SystemPermissionInfo("depa edit","url","部门_修改",menu2));
		session.save(new SystemPermissionInfo("depa list","url","部门_列表",menu2));
		
		// 用户的 增删改查 权限设置
		session.save(new SystemPermissionInfo("user add","url","用户_增加",menu3));
		session.save(new SystemPermissionInfo("user del","url","用户_删除",menu3));
		session.save(new SystemPermissionInfo("user edit","url","用户_修改",menu3));
		session.save(new SystemPermissionInfo("user list","url","用户_列表",menu3));
		
	}
	
}
