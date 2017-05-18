<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统用户</title>
</head>
<body>
	<div>
	  	  <a href="${ctx }/sys/user/addUserUI">新增</a>
	  	  <form action="${ctx }/sys/role/list" method="POST">
			<table style="widht:90%">
				<tbody>
					 <tr>
						<th>登录名</th>
						<th>用户名</th>
						<th>所属部门</th>
						<th>岗位</th>
						<th>备注</th>
						<th>操作</th>
					 </tr>
					 
					<c:choose>
						<c:when test="${not empty userList}">
							<c:forEach items="${userList}" var="user">
								<tr>
									 <td>${user.userName }</td>
									 <td>${user.userName }</td>
									 <td>${user.userName }</td>
									 <td>${user.userName }</td>
									 <td>${user.userName }</td>
									 <td>
										<a href="${ctx }/sys/user/editUserUI/${user.userId}">修改</a>
										<a href="${ctx }/sys/user//delUser/${user.userId}" onclick="return confirm('确认删除?')">删除</a>
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