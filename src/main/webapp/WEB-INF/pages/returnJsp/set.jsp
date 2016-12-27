<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>controller中方法的返回值类型演示</title>
</head>
<body>
<label>前台页面从request中获取后台传递过来的参数：${fn:length(integerSet)}</label>

<%-- 	request:${requestScope.userSet[0].username }<br/>
	request:${requestScope.userSet[1] }<br/><br/><br/> --%>
	
	<label>前台页面从request中获取后台传递过来的参数：</label>
	<%-- request:${requestScope.integerList[0] }<br/>
	request:${requestScope.integerList[1].username }<br/> --%>
	
    <h3>Set</h3>
    <a href="index.do?type=set_user">返回Set&lt;User&gt;</a><br/>
    --->>> ${requestScope.userSet }
    </p>
</body>
</html>