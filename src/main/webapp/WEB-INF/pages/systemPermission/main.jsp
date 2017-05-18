<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统主页</title>
</head>

	<frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
		<frame noresize name="topMenu" scrolling="no" src="${ctx }/sys/home/top" />
		<frameset cols="300,*" id="resize">
			<frame noresize name="menu" scrolling="yes" src="${ctx }/sys/home/left" />
			<frame noresize name="right" scrolling="yes" src="${ctx }/sys/home/right" />
		</frameset>
		<frame noresize name="status_bar"  scrolling="no" src="${ctx }/sys/home/botton" />
	</frameset>
<noframes>
<body>

<%-- 	<jsp:include page="/common/footer.jsp"/> --%>
<!-- 	<script type="text/javascript"> -->
		
<!-- 	</script> -->
<!-- <script type='text/javascript' language='javascript'> -->
<!-- // 	$.prompt('您的账号已过期，请重新登录！', { -->
<!-- // 		title: "提示", -->
<!-- // 		buttons: { '确认': true, '取消': false }, -->
<!-- // 		submit: function(e,v,m,f){ -->
<!-- // 			if(v){ -->
<!-- // 					window.location.href='dao-study/sys/user/loginUI'; -->
<!-- // 				} -->
<!-- // 			} -->
<!-- // 	}); -->
<!-- </script> -->
</body></noframes>
</html>