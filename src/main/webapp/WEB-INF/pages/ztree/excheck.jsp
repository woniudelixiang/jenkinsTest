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
<style type="text/css">

/* .ztree li ul { */
/* 	margin: 0; */
/* 	padding: 0; */
/* } */

/* .ztree li { */
/* 	line-height: 30px; */
/* } */

/* /* 去除a标签的下划线 */ */
/* .ztree li a:hover { */
/* 	text-decoration: none; */
/* 	background-color: #E7E7E7; */
/* } */

/* .ztree li a.level0 { */
/* 	width: 200px; */
/* 	height: 30px; */
/* 	text-align: center; */
/* 	display: block; */
/* 	background-color: #cccccc; */
/* 	background: transparent url(${ctx }/images/abg.png) scroll repeat 0 0; */ 
/* 	border: 1px silver solid; */
/* } */

/* .ztree li a.level0.cur { */
/*  	background-color: #0B61A4; */
/* } */

/* .ztree li a.level0 span { */
/* 	display: block; */
/* 	color: white; */
/* 	padding-top: 3px; */
/* 	font-size: 12px; */
/* 	font-weight: bold; */
/* 	word-spacing: 2px; */
/* 	line-height: 20px; */
/* } */

/* .ztree li a.level0 span.button { */
/* 	float: right; */
/* 	margin-left: 10px; */
/* 	visibility: visible; */
/* 	display: none; */
/* } */

/* .ztree li span.button.switch.level0 { */
/* 	display: none; */
/* } */

/* .ztree li a.level1 { */
/* 	width: 200px; */
/* 	height: 30px; */
/* 	text-align: center; */
/* 	display: block; */
/* 	background-color: #66A3D2; */
/* 	border: 1px silver solid; */
/* } */

/* .ztree li a.level1.cur { */
/* 	background-color: #0B61A4; */
/* } */

/* .ztree li a.level1 span { */
/* 	display: block; */
/* 	color: white; */
/* 	padding-top: 3px; */
/* 	font-size: 12px; */
/* 	font-weight: bold; */
/* 	word-spacing: 2px; */
/* 	line-height: 20px; */
/* } */

/* .ztree li a.level1 span.button { */
/* 	float: right; */
/* 	margin-left: 10px; */
/* 	visibility: visible; */
/* 	display: none; */
/* } */

/* .ztree li span.button.switch.level1 { */
/* 	display: none; */
/* } */

.evn {
	width: 200px;
	height: 30px;
	text-align: center;
	display: block;
	background-color: #66A3D2;
	border: 1px silver solid;
}
</style>

<script type="text/javascript"
	src="${ctx }/js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctx }/js/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx }/js/ztree/zTreeCommon.js"></script>

<SCRIPT type="text/javascript">
	var setting = $ZTC.option({

	});

// 	console.log(JSON.stringify(setting));
	var s = JSON.parse('${menuList }');
// 	console.log("s: " + JSON.stringify(s));

	var zNodes = s;

	$(function() {
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