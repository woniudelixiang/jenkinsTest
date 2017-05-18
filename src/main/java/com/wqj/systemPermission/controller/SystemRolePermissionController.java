package com.wqj.systemPermission.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;
import com.wqj.common.util.ValidateUtils;
import com.wqj.systemPermission.entity.SystemPermissionInfo;
import com.wqj.systemPermission.entity.SystemRoleInfo;
import com.wqj.systemPermission.entity.SystemRolePermissionInfo;
import com.wqj.systemPermission.pojo.ParamBean;

@Controller
@RequestMapping("/sys/role/permission")
public class SystemRolePermissionController extends CommonController{

	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		return "";
	}
	
	/**
	 * 跳转到设置权限页面
	 * @param request
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/setRolePermissionUI/{roleId}")
	public String setRolePermissionUI(HttpServletRequest request, @PathVariable Long roleId) {
		
		// 所有的权限
		List<SystemPermissionInfo> permissionList = systemPermissionService.getRootMenu();
		request.setAttribute("permissionList", permissionList);
		
		SystemRoleInfo systemRole = systemRoleService.get(roleId);
		request.setAttribute("systemRole", systemRole);
		
		List<Long> rolePermissionList = new ArrayList<Long>();
		// 当前角色所拥有的权限
		List<SystemRolePermissionInfo> permissions = systemRole.getPermissions();
		if(permissions != null && permissions.size() > 0){
			for (SystemRolePermissionInfo rolePermission : permissions) {
				 rolePermissionList.add(rolePermission.getSystemPermission().getPermissionId());
			}
		}
		request.setAttribute("rolePermissionList", rolePermissionList);
		return "/systemPermission/rolePermission/edit_role_premis";
	}
	
	/**
	 * 保存权限修改信息
	 * @param request
	 * @param systemRole
	 * @return
	 */
	@RequestMapping("/setRolePermission")
	public String setPermission(HttpServletRequest request, ParamBean paramBean) {
		SystemRoleInfo systemRole = systemRoleService.get(paramBean.getRoleId());
		List<Long> rolePermissionList = paramBean.getRolePermissionList();
		if(ValidateUtils.isNotEmpty(systemRole)){
			// 删除之前拥有的权限
			List<SystemRolePermissionInfo> dbRolePermission = systemRolePermissionService.findDatas("systemRole", systemRole);
			for (SystemRolePermissionInfo systemRolePermission : dbRolePermission) {
				systemRolePermissionService.delete(systemRolePermission);
			}
			// 绑定新的权限
			if(ValidateUtils.isNotEmpty(rolePermissionList) && rolePermissionList.size()>0){
				System.out.println(rolePermissionList.toString());
				for (Long permissionId : rolePermissionList) {
					SystemRolePermissionInfo systemRolePermission = new SystemRolePermissionInfo();
					systemRolePermission.setSystemPermission(new SystemPermissionInfo(permissionId));
					systemRolePermission.setSystemRole(systemRole);
					systemRolePermissionService.save(systemRolePermission);
				}
			}
		}
		return diffRedirect("/sys/role/list");
	}
	
}
