<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>controller中方法返回值为ModelAndView类型演示</title>
</head>
<body>
	page: ${pageScope.message }<br/>
	request: ${requestScope.message }<br/>
	session: ${sessionScope.message }<br/>
	application: ${applicationScope.message }<br/>
	
	<hr>
	request: ${requestScope.user.username }<br/>
	request: ${requestScope.string }<br/>
</body>
</html>