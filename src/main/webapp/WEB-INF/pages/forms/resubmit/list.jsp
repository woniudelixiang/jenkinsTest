<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>防止表单重复提交</title>
</head>
<body>
	<form action="${ctx}/forms/resubmit" method="POST" id="submitForm">
	<c:set value="session_token${suffer }" var="formToken"></c:set>
	<input type="text" name="request_token${suffer }" value="${sessionScope[formToken]}"/>
	<input type="text" name="suffer" value="${suffer }"/>
		用户名：<input type="test" name="name"/>
		<br> <input type="submit" name="提交" /><br><br>
	</form>
	
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	
</body>
</html>