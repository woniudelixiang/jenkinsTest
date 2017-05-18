<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>编辑用户</title>
</head>

<body>
	<form action="${ctx }/sys/user/editUser" method="POST">
		<input type="text" name="userId" value="${systemUser.userId }"/>
		<table>
			<tr>
				<td>用户类型:</td>
				<td>
					<input type="radio" name="userType" value="0" ${systemUser.userType eq 0 ? 'checked' : '' } />普通用户  
					<input type="radio" name="userType" value="1" ${systemUser.userType eq 1 ? 'checked' : '' } />管理员  
				</td>
			</tr>
			
			<tr>
				<td>所属部门:</td>
				<td>
					<select name="department.departmentId" style="width:150px;" >
					<option value="">-请选择-</option>
						<c:forEach var="department" items="${departmentList }">
							<option value="${department.departmentId }" ${department.departmentId eq systemUser.department.departmentId ? 'selected':''}>${department.departmentName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>用户名:</td>
				<td>
					<input type="text" name="userName" value="${systemUser.userName }" />
				</td>
			</tr>
			
			<tr>
				<td>密码:</td>
				<td>
					<input type="text" name="password" value="${systemUser.password }" />
				</td>
			</tr>
			
			<tr>
				<td>岗位（角色）:</td>
				<td>
					<select name="roleIds" style="width:150px;" multiple="multiple" >
					<option value="">-请选择-</option>
						<c:forEach var="parentRole" items="${resultList }">
							<option  value="${parentRole.roleId }" ${fn:contains(roleIds, parentRole.roleId) ? 'selected':''} >${parentRole.roleName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="提交"/>
				</td>
			</tr>
		</table>
	</form>
	
	 <jsp:include page="/common/footer.jsp"/>
	<script type="text/javascript">
	
	</script>
	
</body>
</html>