<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面跳转再返回时保存页面状态</title>
</head>
<body>
	<form action="${ctx}/forms/list" method="POST" id="submitForm">
		<jsp:include page="condition.jsp" >
			<jsp:param value="0" name="type"/>
		</jsp:include>
		<br> <input type="submit" name="提交" /><br><br>
		<a href="Javascript:;" onclick="edit('${ctx}/forms/toEdit/1')">修改</a>
	</form>
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	
</body>
</html>