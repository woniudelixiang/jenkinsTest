<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第二个列表页</title>
</head>
<body>
<a href="javascript:Dback('${ctx }/back/list')">返回</a>
<form action="<c:url value='/back/forwardList'></c:url>" method="post" onsubmit="return sumbitTest();">
	<jsp:include page="../forms/condition.jsp" >
		<jsp:param value="0" name="type"/>
	</jsp:include>
	<input type="submit" value="保存"/>
	
	<jsp:include page="/common/page.jsp">
		<jsp:param name="actionURL" value="dict-category"/>
	</jsp:include>
</form>

<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx }/js/common/common.js"></script>
<script type="text/javascript">
	function sumbitTest(){
		return true;
	}
</script>
</body>
</html>