<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>将校验规则写到控件的自定义属性中演示</title>
<!-- 验证[通过/不通过]时的提示信息样式 -->
<link type="text/css" href="<c:url value='/js/validate/css/validate.css'/>" rel="stylesheet" />
</head>
<body>
	<form id="myform" action="#" method="post">
		<!-- 该种情况：一般验证用户名是必填的，并且不能与数据库的重复 -->
		<label>用户名：</label>
		<input type="text"  name="username" id="username"  placeholder="请输入用户名" 
			validate="{required:true,remote: { url: '<c:url value="/jqueryValidate/remote"/>', type: 'post'},
				messages:{required:'请输入内容'}}" tip="请输入用户名" />
		<br><br> 
		
		<label>密码：</label>
		<input type="password"  name="password" id="password"  placeholder="请输入密码" 
		tip="请输入密码"  autocomplete="off"/>
		<br><br> 
		<input type="submit" value="提交验证"/>
	</form>
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<!-- 漂亮的表单验证需要导入的js文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.validate.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/messages_cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/validate-extends.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/ajaxSubmit.jquery.validate.js"></script> 
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/LoggerUtil.js"></script> 
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/jqueryValidateCommon.js"></script> 
	
	<script type="text/javascript">
		$JVC.bindValidate({
			fromId : 'myform'
		});
	</script>
</body>
</html>