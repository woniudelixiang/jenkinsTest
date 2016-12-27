<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百度插件文件上传</title>
<link rel="stylesheet" href="${ctx}/js/baidu/zyPopup/css/zyPopup.css" type="text/css">
<!-- 引用控制层插件样式 -->
<link rel="stylesheet" href="${ctx}/js/baidu/control/css/zyUpload.css" type="text/css">
<!-- 引用裁剪样式 -->
<link rel="stylesheet" href="${ctx}/js/baidu/jcrop_zh/css/jquery.Jcrop.css" type="text/css">

<!--图片弹出层样式 必要样式-->
<script type="text/javascript" src="${ctx}/js/baidu/jquery-1.7.2.js"></script>
<!-- 引用核心层插件 -->
<script type="text/javascript" src="${ctx}/js/baidu/core/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="${ctx}/js/baidu/control/js/zyUpload.js"></script>
<!-- 引用初始化JS -->
<script type="text/javascript" src="${ctx}/js/baidu/demo.js"></script>
<!-- 引用裁剪JS -->
<script type="text/javascript" src="${ctx}/js/baidu/jcrop_zh/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="${ctx}/js/baidu/jquery.json-2.4.js"></script>

<script type="text/javascript" src="${ctx}/js/baidu/zyPopup/js/zyPopup.js"></script>

</head>
<body>
	<h1 style="text-align:center;">zyFile示例</h1>
	<div id="demo" class="demo"></div>  
	
	 
</body>
</html>