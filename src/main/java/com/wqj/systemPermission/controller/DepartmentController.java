package com.wqj.systemPermission.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;
import com.wqj.systemPermission.entity.DepartmentInfo;

@Controller
@RequestMapping("/sys/department")
public class DepartmentController extends CommonController{

	/**
	 * 部门列表
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request,Long departmentId) {
		List<DepartmentInfo> departmentList = new ArrayList<DepartmentInfo>();
		if(departmentId == null){
			departmentList = departmentService.getRootDepartmentList();
		}else{
			departmentList = departmentService.getChildDepartmentList(departmentId);
		}
		request.setAttribute("departmentList", departmentList);
		return "/systemPermission/department/list_department";
	}
	
	/**
	 * 跳转到添加部门页面
	 * @param request
	 * @return
	 */
	@RequestMapping("addDepartmentUI")
	public String adddepartmentUI(HttpServletRequest request) {
		// 下拉框
		List<DepartmentInfo> departmentList = departmentService.getRootDepartmentList();
		List<DepartmentInfo> resultList = new ArrayList<DepartmentInfo>();
		departmentService.getDepartmentTreeList(departmentList, "", resultList);
		request.setAttribute("resultList", resultList);
		return "/systemPermission/department/add_department";
	}
	
	/**
	 * 保存新增的部门
	 * @param request
	 * @return
	 */
	@RequestMapping("/addDepartment")
	public String adddepartment(HttpServletRequest request, DepartmentInfo department) {
		if(department.getParentDepartment().getDepartmentId()==null){
			department.setParentDepartment(null);
		}
		departmentService.save(department);
		return redirect("list");
	}
	
	/**
	 * 跳转到修改页面
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping("editDepartmentUI/{departmentId}")
	public String editdepartmentUI(HttpServletRequest request,@PathVariable Long departmentId) {
		DepartmentInfo department = departmentService.get(departmentId);
		request.setAttribute("department", department);
		return "/systemPermission/department/edit_department";
	}
	
	/**
	 * 保存修改信息
	 * @param request
	 * @param systemdepartment
	 * @return
	 */
	@RequestMapping("/editDepartment")
	public String editDepartment(HttpServletRequest request, DepartmentInfo department) {
		if(department.getParentDepartment().getDepartmentId()==null){
			department.setParentDepartment(null);
		}
		DepartmentInfo departmentInfo = departmentService.get(department.getDepartmentId());
		departmentInfo.setDepartmentName(department.getDepartmentName());
		departmentInfo.setDepartmentDescription(department.getDepartmentDescription());
		departmentInfo.setParentDepartment(department.getParentDepartment());
		
		departmentService.update(departmentInfo);
		return redirect("list");
	}
	
	/**
	 * 删除
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping("/delDepartment/{departmentId}")
	public String delDepartment(HttpServletRequest request,@PathVariable Long departmentId) {
		DepartmentInfo department = departmentService.get(departmentId);
		departmentService.delete(department);
		return  redirect("list");
	}
	
}
