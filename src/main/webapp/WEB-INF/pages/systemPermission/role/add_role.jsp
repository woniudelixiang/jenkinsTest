<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加角色（部门）</title>
</head>

<body>
	<form action="${ctx }/sys/role/addRole" method="POST">
		<table>
			<tr>
				<td>上级:</td>
				<td>
					<select name="parentRole.roleId" style="width:150px;" >
					<option value="">-请选择-</option>
						<c:forEach var="parentRole" items="${resultList }">
							<option value="${parentRole.roleId }" >${parentRole.roleName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>角色名称:</td>
				<td>
					<input type="text" name="roleName">
				</td>
			</tr>
			
			<tr>
				<td>角色说明:</td>
				<td>
					<input type="text" name="roleDescription">
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