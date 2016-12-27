<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/date/WdatePicker.js"></script> 
</head>
<body>
	<form action="<c:url value='/user/toLogin'></c:url>" method="post">
		<!-- <input type="text" name="filter_LIKES_username" value=""/> -->
		<br><br>
<!-- 		开始时间：<input type="text" name="filter_BAL_createDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/> -->
<!-- 		结束时间：<input type="text" name="filter_BAL_createDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/> -->
		<br><br>
		<input type="checkbox" name="filter_MEQL_userId" value="1"/>第一项<br>
		<input type="checkbox" name="filter_MEQL_userId" value="2"/>第二项<br>
		<input type="checkbox" name="filter_MEQL_userId" value="3"/>第三项<br>
		<input type="submit" value="查询"/>
	</form>
</body>
</html>