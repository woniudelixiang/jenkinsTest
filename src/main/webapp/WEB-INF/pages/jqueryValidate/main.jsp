<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jqueryValidate 演示</title>
</head>
<body>
	<label>方式1：</label>
	<input type="button" value="将校验规则写到控件的class属性中演示【没有封装】" onclick="jump('1')"/> 
	<input type="button" value="将校验规则写到控件的class属性中演示【封装】" onclick="jump('1_1')"/> <br/><br/>
	
	<label>方式2：</label>
	<input type="button" value="将校验规则写到 js代码中演示【没有封装】" onclick="jump('2')"/>  
	<input type="button" value="将校验规则写到 js代码中演示【封装】" onclick="jump('2_1')"/> <br/><br/>
	
	<label>方式3：</label>
	<input type="button" value="将校验规则写到控件的自定义属性中演示【没有封装】" onclick="jump('3')"/>  
	<input type="button" value="将校验规则写到控件的自定义属性中演示【封装】" onclick="jump('3_1')"/> <br/><br/>
	
	<label>方式4：</label>
	<input type="button" value="ajax+jquery-validate【没有封装】" onclick="jump('4')"/>  
	<input type="button" value="ajax+jquery-validate【封装】" onclick="jump('4_1')"/> <br/><br/>
	
	
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		function jump(type){
			var url = '${ctx }/jqueryValidate/list'+type;
			window.location.href = url;
		}
	</script>
</body>
</html>