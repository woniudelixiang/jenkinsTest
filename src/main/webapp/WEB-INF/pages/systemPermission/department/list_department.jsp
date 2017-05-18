<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统部门</title>
</head>
<body>
	<div>
<%-- 	  	  <a href="${ctx }/sys/department/addDepartmentUI">新增</a> --%>
	  	  <u:a href="${ctx }/sys/department/addDepartmentUI">新增</u:a>
	  	  <form action="${ctx }/sys/department/list" method="POST">
			<table style="widht:90%">
				<tbody>
					 <tr>
						<th>部门名称</th>
						<th>上级</th>
						<th>操作</th>
					 </tr>
					 
					<c:choose>
						<c:when test="${not empty departmentList}">
							<c:forEach items="${departmentList}" var="department">
								<tr>
									 <td><a href="${ctx }/sys/department/list?departmentId=${department.departmentId}">${department.departmentName }</a></td>
									 <td>${department.parentDepartment.departmentName }</td>
									 <td>
										<u:a href="${ctx }/sys/department/editDepartmentUI/${department.departmentId}">修改</u:a>
<%-- 										<a href="${ctx }/sys/department/editDepartmentUI/${department.departmentId}">修改</a> --%>
										<u:a href="${ctx }/sys/department/delDepartment/${department.departmentId}" onclick="return confirm('确认删除?')">删除</u:a>
<%-- 										<a href="${ctx }/sys/department//delDepartment/${department.departmentId}" onclick="return confirm('确认删除?')">删除</a> --%>
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