<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<div style="${param.type eq 1 ? 'display:none' : '' }">
	用户名：<input type="test" name="filterParamMap['LIKES_username']" value="${filterParam.filterParamMap['LIKES_username']}" />
	时间： <input type="test" name="filterParamMap['BAL_createDate']" value="${filterParam.filterParamMap['BAL_createDate'][0]}" />--
	    <input type="test" name="filterParamMap['BAL_createDate']" value="${filterParam.filterParamMap['BAL_createDate'][1]}" /><br><br>
</div>
