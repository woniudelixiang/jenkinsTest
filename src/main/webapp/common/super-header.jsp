<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="jan_top_box">
	<div class="jan_top_div">
		<div class="left"><a href="#"><img src="${pageContext.request.contextPath}/images/seniorInfo/log.png" width="248" height="23"></a></div>
		<div class="right">
			<p class="p1">${currentSenior.headPicUrl}</p>
			<p class="p2">${currentUser.userName}</p>
<%-- 			<p class="p3"><a href="${pageContext.request.contextPath}/seniorInfo/listReward">&nbsp;&nbsp;</a></p> --%>
			<p class="p4"><a href="${pageContext.request.contextPath}/login/logout">退出</a></p>
		</div>
	</div>
</div>