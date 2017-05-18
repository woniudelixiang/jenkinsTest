<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="u" uri="ueye"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色_权限</title>
	<link href="${ctx }/css/jquery-treeview/jquery.treeview.css" rel="stylesheet" />
</head>
<body>
	<form action="${ctx }/sys/role/permission/setRolePermission">
		<input type="hidden" name="roleId" value="${systemRole.roleId }" />
		<input type="checkbox" name="checkAll" id="checkAll" onclick="$('[name=rolePermissionList][type=checkbox]').attr('checked',this.checked)"/>
		<label for="checkAll">全选</label><br/><br>
		
		<ul id="menuTree">
			<c:forEach var="permission" items="${permissionList }">
	<%-- 			<input type="checkbox" id="cb_${permission.permissionId }" name="rolePermissionList" value="${permission.permissionId }" ${fn:contains(rolePermissionList, permission.permissionId) ? 'checked':''} /> --%>
	<%-- 			<label for="cb_${permission.permissionId }">${permission.permissionName }</label><br/> --%>
				
				<li>
					<input type="checkbox" id="cb_${permission.permissionId }" name="rolePermissionList" value="${permission.permissionId }" ${fn:contains(rolePermissionList, permission.permissionId) ? 'checked':''} />
		            <label for="cb_${permission.permissionId }">${permission.permissionName }</label>
					<ul>
						<c:forEach var="child" items="${permission.childs }">
							<li>
								 <input type="checkbox" id="cb_${child.permissionId }" name="rolePermissionList" value="${child.permissionId }" ${fn:contains(rolePermissionList, child.permissionId) ? 'checked':''} />
			           			 <label for="cb_${child.permissionId }">${child.permissionName }</label>
								<ul>
									<c:forEach var="child2" items="${child.childs }">
										<li>
											<input type="checkbox" id="cb_${child2.permissionId }" name="rolePermissionList" value="${child2.permissionId }" ${fn:contains(rolePermissionList, child2.permissionId) ? 'checked':''} />
			           			 			<label for="cb_${child2.permissionId }">${child2.permissionName }</label>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>
		<input type="submit" value="提交"/>
	</form>
	
	 <jsp:include page="/common/footer.jsp"/>
	 <script type="text/javascript">
	 	$("#menuTree").treeview();
	 	
	 	// 文档加载完后执行
	 	$(function(){
	 		$("#menuTree").on("click","[name=rolePermissionList][type=checkbox]",function(){
		 		// 当取消或者选中一个权限时，也同时取消或者选中所有的下级权限
		 		$(this).siblings("ul").find("input[name=rolePermissionList][type=checkbox]").attr("checked",this.checked);
		 		
		 		// 当选中一个权限时，也同时选中直接上级权限
		 		if(this.checked){
			 		$(this).parents("li").children("input[name=rolePermissionList][type=checkbox]").attr("checked",this.checked);
		 		}
		 	});
	 		
	 	})
	 	
	 	
	 </script>
</body>
</html>