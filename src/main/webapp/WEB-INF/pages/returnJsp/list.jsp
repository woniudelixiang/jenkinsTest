<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>controller中方法返回值为List类型演示</title>
</head>
<body>
	request:${requestScope.userList[0].username }<br/>
	request:${requestScope.userList[1] }<br/><br/><br/>
	<hr>
	request:${requestScope.integerList[0] }<br/>
	request:${requestScope.integerList[1].username }<br/>
</body>
</html>