<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>左侧</title>
</head>

<body>
	<c:forEach var="menu" items="${menuList }">
		<c:if test="${CURRENT_USER.isHaveMenu(menu.permissionId) }">
			<div onclick="menuClick(this);">${menu.permissionName }</div>
		</c:if>
		<ul>
			<c:forEach var="child" items="${menu.childs }">
				<c:if test="${CURRENT_USER.isHaveMenu(child.permissionId) }">
					<li>
						<a target="right" href="${ctx }/${child.url }">${child.permissionName }</a>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</c:forEach>
	
	<jsp:include page="/common/footer.jsp"/>
	<script type="text/javascript">
	function menuClick(divObj){
		$this = $(divObj);
		$this.next().toggle();
		
	}
	</script>
</body>
</html>