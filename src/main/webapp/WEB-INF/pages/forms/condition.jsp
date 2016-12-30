<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="${param.type eq 1 ? 'display:none' : '' }">
	用户名：<input type="test" name="cond['LIKES_username']" value="${condMap.cond['LIKES_username']}" /><br><br>
	密码： <input type="test" name="cond['LIKES_password']" value="${condMap.cond['LIKES_password']}" /><br><br>
<%-- 	用户名：<input type="test" name="filter_LIKES_username" value="${condMap.cond['filter_LIKES_username']}" /><br><br> --%>
<%-- 	密码： <input type="test" name="filter_LIKES_password" value="${condMap.cond['filter_LIKES_password']}" /><br><br> --%>
</div>
