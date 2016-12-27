<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO - Simple Data</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/css/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="${ctx }/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/js/ztree/zTreeCommon.js"></script>

<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core.js"></script>

<SCRIPT type="text/javascript">

	var setting = $ZTC.option({
		
	});
	
	console.log(JSON.stringify(setting));
	var s = JSON.parse('${menuList }');
	
	var zNodes = s;

	$(function(){
		$ZTC.init('treeDemo', setting, zNodes);
	});

</SCRIPT>
</HEAD>

<BODY>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	
</BODY>
</HTML>