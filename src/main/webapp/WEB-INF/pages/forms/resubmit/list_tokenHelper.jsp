<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="tokenTag" prefix="tokenTag"%>  


<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>防止表单重复提交</title>
</head>
<body>
	<form action="${ctx}/forms/tokenHelper" method="POST" id="submitForm" ><!-- target="iframeData" -->
		<tokenTag:token/>
		用户名：<input type="test" name="name"/>
		<br> <input type="submit" name="提交" />
		<br><br>
	</form>
	
	<iframe name ='iframeData' style ='display: none;'>
	</iframe>
	
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>