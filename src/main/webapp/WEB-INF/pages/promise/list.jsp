<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Promise初级教程</title>
</head>
<body>
		
		
		
		
	

	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		// ex1
		var startTime = Date.now();
		console.log('start', startTime);
		
		var handler = new Promise(function (resolve, reject) {
		    setTimeout(function () {
		    	resolve('done', Date.now() - startTime);
		    }, 500);
		}); 
		
		handler.then(function (val) {
		    console.log('then resolve', val, Date.now() - startTime);
		}, function (e) {
		    console.log('then reject', e, Date.now() - startTime);
		});
	</script>
	
	
	
</body>
</html>