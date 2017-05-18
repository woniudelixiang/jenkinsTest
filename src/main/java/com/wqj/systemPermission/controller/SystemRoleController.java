package com.wqj.systemPermission.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;
import com.wqj.systemPermission.entity.SystemRoleInfo;

@Controller
@RequestMapping("/sys/role")
public class SystemRoleController extends CommonController{

	/**
	 * 角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request,Long roleId) {
		List<SystemRoleInfo> roleList = new ArrayList<SystemRoleInfo>();
		if(roleId == null){
			roleList = systemRoleService.getRootRoleList();
		}else{
			roleList = systemRoleService.getChildRoleList(roleId);
		}
		request.setAttribute("roleList", roleList);
		return "/systemPermission/role/list_role";
	}
	
	/**
	 * 跳转到添加角色页面
	 * @param request
	 * @return
	 */
	@RequestMapping("addRoleUI")
	public String addRoleUI(HttpServletRequest request) {
		// 下拉框
		List<SystemRoleInfo> rootRoleList = systemRoleService.getRootRoleList();
		List<SystemRoleInfo> resultList = new ArrayList<SystemRoleInfo>();
		systemRoleService.getRoleTreeList(rootRoleList, "", resultList);
		request.setAttribute("resultList", resultList);
		
		return "/systemPermission/role/add_role";
	}
	
	/**
	 * 保存新增的角色
	 * @param request
	 * @return
	 */
	@RequestMapping("/addRole")
	public String addRole(HttpServletRequest request, SystemRoleInfo systemRole) {
		if(systemRole.getParentRole().getRoleId()==null){
			systemRole.setParentRole(null);
		}
		systemRoleService.save(systemRole);
		return redirect("list");
	}
	
	/**
	 * 跳转到修改页面
	 * @param request
	 * @param roleId
	 * @return
	 */
	@RequestMapping("editRoleUI/{roleId}")
	public String editRoleUI(HttpServletRequest request,@PathVariable Long roleId) {
		List<SystemRoleInfo> parentRoleList = systemRoleService.findAll();
		request.setAttribute("parentRoleList", parentRoleList);
		SystemRoleInfo systemRole = systemRoleService.get(roleId);
		request.setAttribute("systemRole", systemRole);
		return "/systemPermission/role/edit_role";
	}
	
	/**
	 * 保存修改信息
	 * @param request
	 * @param systemRole
	 * @return
	 */
	@RequestMapping("/editRole")
	public String editRole(HttpServletRequest request, SystemRoleInfo systemRole) {
		if(systemRole.getParentRole().getRoleId()==null){
			systemRole.setParentRole(null);
		}
		SystemRoleInfo systemRoleInfo = systemRoleService.get(systemRole.getRoleId());
		systemRoleInfo.setRoleName(systemRole.getRoleName());
		systemRoleInfo.setRoleDescription(systemRole.getRoleDescription());
		systemRoleInfo.setParentRole(systemRole.getParentRole());
		systemRoleService.update(systemRoleInfo);
		return redirect("list");
	}
	
	/**
	 * 删除
	 * @param request
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/delRole/{roleId}")
	public String delRole(HttpServletRequest request,@PathVariable Long roleId) {
		SystemRoleInfo systemRole = systemRoleService.get(roleId);
		systemRoleService.delete(systemRole);
		return  redirect("list");
	}
	
}
