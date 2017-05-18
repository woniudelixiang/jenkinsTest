package com.wqj.systemPermission.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;
import com.wqj.common.util.ValidateUtils;
import com.wqj.systemPermission.entity.DepartmentInfo;
import com.wqj.systemPermission.entity.SystemRoleInfo;
import com.wqj.systemPermission.entity.SystemUserInfo;
import com.wqj.systemPermission.entity.SystemUserRoleInfo;
import com.wqj.systemPermission.pojo.ParamBean;


@Controller
@RequestMapping("/sys/user")
public class SystemUserController extends CommonController{

	@RequestMapping("loginUI")
	public String loginUI(HttpServletRequest request) {
		return "systemPermission/user/login";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("login")
	public String login(HttpServletRequest request, SystemUserInfo userInfo) {
		List<SystemUserInfo> userList = systemUserService.findUserByIdentity(userInfo);
		if(ValidateUtils.isNotEmpty(userList)  && userList.size() == 1){
			SystemUserInfo systemUser = userList.get(0);
			// 存放到session中
//			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();  
			HttpSession session = request.getSession();
			
			// 第一个问题解决：不同的用户使用同一个浏览器进行进行登录的时候，公用同一个session，最后一个人员登录的时候，清除之前人session中的相关信息
			System.out.println("sessionId : " + session.getId());
			Enumeration<String> attributeNames = session.getAttributeNames();
			while(attributeNames.hasMoreElements()){
				String element = attributeNames.nextElement();
				System.out.println("删除session中国的属性有： " + element);
				session.removeAttribute(element);
			}
			
			// 第二个问题解决：（用户不能同时多次登录）同一个用户不能在不同的浏览器中同时登录，如果登录的话，则后登录的会把之前登录的被迫下线
			ServletContext sc = session.getServletContext();
			Map<SystemUserInfo,HttpSession> onlineUserMap = (Map<SystemUserInfo, HttpSession>) sc.getAttribute("onlineUserMap");
			if(onlineUserMap.containsKey(systemUser)){
				System.out.println("账号在其他地方登陆了，销毁之前的session.......................................................");
				onlineUserMap.get(systemUser).invalidate();  // 销毁session
			}
			
			session.setAttribute("CURRENT_USER", systemUser);
			return diffRedirect("/sys/home/main");
		}
		return "systemPermission/user/login";
	}
	
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request) {
		request.getSession().invalidate();
		// 存放到session中
//		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();  
//		session.removeAttribute("CURRENT_USER");
		return "systemPermission/user/login";
	}
	
	
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		List<SystemUserInfo> userList = systemUserService.getUserList();
		request.setAttribute("userList", userList);
		return "systemPermission/user/list_user";
	}
	
	/**
	 * 跳转到添加角色页面
	 * @param request
	 * @return
	 */
	@RequestMapping("addUserUI")
	public String addUserUI(HttpServletRequest request) {
		// 下拉框
		List<SystemRoleInfo> rootRoleList = systemRoleService.getRootRoleList();
		List<SystemRoleInfo> resultList = new ArrayList<SystemRoleInfo>();
		systemRoleService.getRoleTreeList(rootRoleList, "", resultList);
		request.setAttribute("resultList", resultList);
		
		// 部门
		List<DepartmentInfo> departments = departmentService.getRootDepartmentList();
		List<DepartmentInfo> departmentList = new ArrayList<DepartmentInfo>();
		departmentService.getDepartmentTreeList(departments, "", departmentList);
		request.setAttribute("departmentList", departmentList);
		return "/systemPermission/user/add_user";
	}
	
	/**
	 * 保存新增的角色
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request, SystemUserInfo systemUser, ParamBean paramBean) {
		if(systemUser.getDepartment().getDepartmentId() == null){
			systemUser.setDepartment(null);
		}
		List<Long> roleIds = paramBean.getRoleIds();
		if(ValidateUtils.isNotEmpty(roleIds)){
			List<SystemUserRoleInfo> userRoles = SystemUserRoleInfo.getUserRoleList(roleIds,systemUser);
			systemUser.setRoles(userRoles);
		}
		systemUserService.save(systemUser);
		return redirect("list");
	}
	
	/**
	 * 跳转到修改页面
	 * @param request
	 * @param roleId
	 * @return
	 */
	@RequestMapping("editUserUI/{userId}")
	public String editRoleUI(HttpServletRequest request,@PathVariable Long userId) {
		SystemUserInfo systemUser = systemUserService.get(userId);
		request.setAttribute("systemUser", systemUser);
		
		// 下拉框
		List<SystemRoleInfo> rootRoleList = systemRoleService.getRootRoleList();
		List<SystemRoleInfo> resultList = new ArrayList<SystemRoleInfo>();
		systemRoleService.getRoleTreeList(rootRoleList, "", resultList);
		request.setAttribute("resultList", resultList);
		
		// 部门
		List<DepartmentInfo> departments = departmentService.getRootDepartmentList();
		List<DepartmentInfo> departmentList = new ArrayList<DepartmentInfo>();
		departmentService.getDepartmentTreeList(departments, "", departmentList);
		request.setAttribute("departmentList", departmentList);
		
		//角色回显
		List<Long> roleIds = new ArrayList<Long>();
		List<SystemUserRoleInfo> roles = systemUser.getRoles();
		if(ValidateUtils.isNotEmpty(roles)){
			for (SystemUserRoleInfo userRole : roles) {
				roleIds.add(userRole.getSystemRole().getRoleId());
			}
		}
		request.setAttribute("roleIds", roleIds);
		
		return "/systemPermission/user/edit_user";
	}
	
	/**
	 * 保存修改信息
	 * @param request
	 * @param systemRole
	 * @return
	 */
	@RequestMapping("/editUser")
	public String editRole(HttpServletRequest request, SystemUserInfo systemUser, ParamBean paramBean) {
		if(systemUser.getDepartment().getDepartmentId() == null){
			systemUser.setDepartment(null);
		}
		SystemUserInfo systemUserInfo = systemUserService.get(systemUser.getUserId());
		systemUserInfo.setDepartment(systemUser.getDepartment());  // 部门
		systemUserInfo.setUserName(systemUser.getUserName());  // 用户名
		systemUserInfo.setPassword(systemUser.getPassword());   // 密码
		
		// 删除之前中间表的数据
		List<SystemUserRoleInfo> findDatas = systemUserRoleService.findDatas("systemUser", systemUserInfo);
		if(ValidateUtils.isNotEmpty(findDatas)){
			for (SystemUserRoleInfo systemUserRoleInfo : findDatas) {
				systemUserRoleService.delete(systemUserRoleInfo);
			}
		}
		
		List<Long> roleIds = paramBean.getRoleIds();
		if(ValidateUtils.isNotEmpty(roleIds)){
			List<SystemUserRoleInfo> userRoles = SystemUserRoleInfo.getUserRoleList(roleIds,systemUserInfo);
			systemUserInfo.setRoles(userRoles);
		}
		
		
		systemUserService.update(systemUserInfo);
		
		return redirect("list");
	}
	
	/**
	 * 删除
	 * @param request
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/delUser/{userId}")
	public String delRole(HttpServletRequest request,@PathVariable Long userId) {
		SystemUserInfo systemUser = systemUserService.get(userId);
		systemUserService.delete(systemUser);
		return  redirect("list");
	}
}
