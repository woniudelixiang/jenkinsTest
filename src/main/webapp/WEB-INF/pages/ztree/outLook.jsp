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
.ztree * {font-size: 10pt;font-family:"Microsoft Yahei",Verdana,Simsun,"Segoe UI Web Light","Segoe UI Light","Segoe UI Web Regular","Segoe UI","Segoe UI Symbol","Helvetica Neue",Arial}
.ztree li ul{ margin:0; padding:0}
.ztree li {line-height:30px;}
.ztree li a {width:200px;height:30px;padding-top: 0px;}
.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
.ztree li a span.button.switch {visibility:hidden}
.ztree.showIcon li a span.button.switch {visibility:visible}
.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
.ztree li span {line-height:30px;}
.ztree li span.button {margin-top: -7px;}
.ztree li span.button.switch {width: 16px;height: 16px;}

.ztree li a.level0 span {font-size: 150%;font-weight: bold;}
.ztree li span.button {background-image:url("./left_menuForOutLook.png"); *background-image:url("./left_menuForOutLook.gif")}
.ztree li span.button.switch.level0 {width: 20px; height:20px}
.ztree li span.button.switch.level1 {width: 20px; height:20px}
.ztree li span.button.noline_open {background-position: 0 0;}
.ztree li span.button.noline_close {background-position: -18px 0;}
.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}
	</style>

</style>

<script type="text/javascript" src="${ctx }/js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx }/js/ztree/zTreeCommon.js"></script>

<SCRIPT type="text/javascript">

	var setting = $ZTC.option({
		
	});
	
// 	console.log(JSON.stringify(setting));
	var s = JSON.parse('${menuList }');
// 	console.log("s: "+JSON.stringify(s));
	
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