<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>extend演示</title>
</head>
<body>
	
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>

<script type="text/javascript">

var defaultParam = { name: "John", location: {city: "Boston",county:"USA"}};
var freeParam = { last: "Resig", location: {state: "MA",county:"China"} };

var result1 = $.extend( true,  {}, defaultParam, freeParam ); 
var result2 = $.extend(defaultParam, freeParam ); 

	if(result2 == defaultParam){
		console.log("相等");
	}else{
		console.log("不相等");
	}


</script>
</body>
</html>