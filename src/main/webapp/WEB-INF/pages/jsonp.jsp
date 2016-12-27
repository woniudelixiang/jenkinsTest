<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsonp演示</title>
</head>
<body>

<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	function demo(data){
		alert(" demo: "+JSON.stringify(data)); 
	}

	$.ajax({
		type:"POST",
		url:"${ctx}/jsonp/ajax",
		data:'{name="John"}',
		dataType:'jsonp',
		//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
		jsonp:'methodName',
		//动态指定回调的名称,服务器要获取该名称来作为返回的函数名称
		jsonpCallback:'demo',
		//请求成功的回调函数
		success:function(data){
			alert("success: "+JSON.stringify(data)); 
		},
		//请求失败的回调函数
		error: function(data){
			alert("error: "+JSON.stringify(data)); 
		}
	
	});
</script>
</body>
</html>