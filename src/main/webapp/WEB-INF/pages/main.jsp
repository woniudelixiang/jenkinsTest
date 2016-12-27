<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>列表页面</title>
</head>
<body>
	<input type="button" onclick="updateUser(2)" value="修改用户信息"/>

	<script src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
		function updateUser(id){
			window.location.href="${ctx}/jfujc/add/"+id
		}
	</script>
</body>
</html>
