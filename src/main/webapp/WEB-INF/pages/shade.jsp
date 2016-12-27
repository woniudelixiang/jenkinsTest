<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>遮罩层演示</title>
<style type="text/css">
.tan-wrap{
	display: none;
	width: 100%;
	overflow: hidden;
	position: absolute;
	top: 0;
	left: 0;
	background: rgba(0,0,0,.2);
}
</style>
</head>
<body>
	<input type="button" class="shade" value="打开遮罩层"/>
	<div class="tan-wrap"></div>
	
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	//遮罩层
	var pageW = document.documentElement.clientWidth;  // 可见区域宽度
	var pageH = document.documentElement.clientHeight; // 可见区域高度
	$(".tan-wrap").css({"height":pageH+"px","width":pageW+"px"});
	$(".shade").click(function(){
		//遮罩层打开
		$(".tan-wrap").show();
	});
</script>

</body>
</html>