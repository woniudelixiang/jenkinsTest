<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="jan_left">
	<dl class="dl1">
		<dt>管理</dt>
		<%-- <dd>
			<a href="${pageContext.request.contextPath}/seniorInfo/listMsg" <c:if test="${param.index eq 1}">class="now"</c:if>>消息管理</a>
		</dd> --%>
<%-- 		<dd>
			<a href="${pageContext.request.contextPath}/seniorInfo/listReward" <c:if test="${param.index eq 1}">class="now"</c:if>>消息管理</a>
		</dd> --%>
<%-- 	   <dd>
			<a href="${pageContext.request.contextPath}/seniorInfo/listTopic" <c:if test="${param.index eq 2}">class="now"</c:if>>我的动态</a>
		</dd>
		<dd>
			<a href="${pageContext.request.contextPath}/seniorInfo/listCvst" <c:if test="${param.index eq 3}">class="now"</c:if>>我的话题</a>
		</dd>
		<dd>
			<a href="${pageContext.request.contextPath}/social/publicClass/myPublicClass/${currentUser.id}" <c:if test="${param.index eq 4}">class="now"</c:if>>我的公开课</a>
		</dd>
		<dd>
			<a href="${pageContext.request.contextPath}/social/appointment/myAppointmentList/${currentUser.id}" <c:if test="${param.index eq 5}">class="now"</c:if>>我的预约</a>
		</dd>
		<dd>
			<a href="${pageContext.request.contextPath}/social/appointment/appointmentMyList/${currentUser.id}" <c:if test="${param.index eq 6}">class="now"</c:if>>预约我的</a>
		</dd>
		<dd>
			<a href="${pageContext.request.contextPath}/social/personalCenter/baseDatum/${currentUser.id}" <c:if test="${param.index eq 7}">class="now"</c:if>>个人中心</a>
		</dd>  --%>
		<dd>
			<a href="${pageContext.request.contextPath}/console/topic/myDatum" <c:if test="${param.index eq 1}">class="now"</c:if>>我的资料</a>
		</dd>
	</dl>
</div>
