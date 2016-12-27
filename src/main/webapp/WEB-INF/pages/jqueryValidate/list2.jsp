<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>将校验规则写到 js代码中演示</title>
<!-- 验证[通过/不通过]时的提示信息样式 -->
<link type="text/css" href="<c:url value='/js/validate/css/validate.css'/>" rel="stylesheet" />
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
<style type="text/css">
input:-webkit-autofill {
 -webkit-box-shadow: 0px 0px 0px 1000px white inset;  /* 使用足够大的纯色内阴影覆盖黄色背景 */ 
 } 
</style>  
</head>
<body>
	<form id="myform" action="${ctx }/jqueryValidate/form" method="post" class="form-horizontal" autocomplete="off">
		<!-- 该种情况：一般验证用户名是必填的，并且不能与数据库的重复 -->
		<div class="form-group">
			<label class="col-sm-1 control-label">用户名：</label>
			<div class="col-sm-2">
				<input type="text" id="username"  name="username"  class="form-control"  placeholder="请输入用户名"
					  validate= "{required:true}"   tip="用户名只能包含中文,英文和_" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-1 control-label">密码：</label>
			<div class="col-sm-2">
				<input type="password"  name="password" id="password"  class="form-control" placeholder="请输入密码"  
					validate= "{required:true}"  tip="请输入密码" />
			</div>
		</div>
		
		<div class="btn-group"> 
			<button type="button" class="btn btn-success addValidate" >提交验证的表单</button>
		</div>
	</form>

<%-- 	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script> --%>
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<!-- 漂亮的表单验证需要导入的js文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.validate.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/messages_cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/validate-extends.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/LoggerUtil.js"></script> 
<%-- 	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/jqueryValidateAjaxCommon.js"></script>  --%>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/jquery-validate-util.js"></script> 
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/ajaxCommon.js"></script> 
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/serializeJson.js"></script> 
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jsonCommon.js"></script> 
	
	<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/list2.js"></script> 
	
</body>
</html>