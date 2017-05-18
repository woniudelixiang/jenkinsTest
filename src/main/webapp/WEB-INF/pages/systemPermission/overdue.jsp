<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>过期提示</title>
</head>
<body>
	<jsp:include page="/common/footer.jsp"/>
	<script type='text/javascript' language='javascript'>

	
	$(function(){
		// 在页面被嵌套的时候，刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}else{
			if(confirm('您的账号已过期，请重新登录！')){
	 			window.location.href='${ctx }/sys/user/loginUI'; 
			}
		}
	});
	</script>
</body>
</html>