<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cookie演示</title>

</head>
<body>
	<input type="button" onclick="jumpUrl('${ctx }/testCookie/setCookie')" value="setCookie"/>
	<input type="button" onclick="jumpUrl('${ctx }/testCookie/getCookie')" value="getCookie"/>

	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	function jumpUrl(url){
		window.location.href = url;
	}
       
    </script>
</head>
</html>