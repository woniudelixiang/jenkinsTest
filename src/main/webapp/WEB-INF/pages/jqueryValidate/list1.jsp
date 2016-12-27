<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>将校验规则写到控件的class属性中演示</title>
<style type="text/css">
label.tip{
    margin-left: 5px;
    padding-left: 20px;
    color: #aaa;
    background: url(${ctx}/images/create_new.jpg) no-repeat left center;
}
</style>
</head>
<body>


	
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<!-- 漂亮的表单验证需要导入的js文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.validate.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/messages_cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/validate-extends.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/ajaxSubmit.jquery.validate.js"></script> 
	
</body>
</html>