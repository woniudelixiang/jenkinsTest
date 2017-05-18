<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户登录</title>
</head>

<body>
	<form action="${ctx }/sys/user/login" method="POST">
		<table>
			<tr>
				<td>用户名:</td>
				<td>
					<input type="text" name="userName">
				</td>
			</tr>
			
			<tr>
				<td>密码:</td>
				<td>
					<input type="text" name="password">
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="提交"/>
				</td>
			</tr>
		</table>
	</form>
	
	 <jsp:include page="/common/footer.jsp"/>
	<script type="text/javascript">
		// 在页面被嵌套的时候，刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
	</script>
	
</body>
</html>