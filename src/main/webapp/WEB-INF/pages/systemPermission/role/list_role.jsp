<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统角色（部门）</title>
</head>
<body>
	<div>
	  	  <a href="${ctx }/sys/role/addRoleUI">新增</a>
	  	  <form action="${ctx }/sys/role/list" method="POST">
			<table style="widht:90%">
				<tbody>
					 <tr>
						<th>岗位名称</th>
						<th>上级</th>
						<th>操作</th>
					 </tr>
					 
					<c:choose>
						<c:when test="${not empty roleList}">
							<c:forEach items="${roleList}" var="role">
								<tr>
									 <td><a href="${ctx }/sys/role/list?roleId=${role.roleId}">${role.roleName }</a></td>
									 <td>${role.parentRole.roleName }</td>
									 <td>
										<a href="${ctx }/sys/role/editRoleUI/${role.roleId}">修改</a>
										<a href="${ctx }/sys/role//delRole/${role.roleId}" onclick="return confirm('确认删除?')">删除</a>
										<a href="${ctx }/sys/role/permission/setRolePermissionUI/${role.roleId}">设置权限</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</form>
	  </div>
	 
	 <jsp:include page="/common/footer.jsp"/>
	 <script type="text/javascript">
	 	  
	 
	 
	 </script>
</body>
</html>