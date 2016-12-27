<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${fn:length(page.datas) == 0}">
	<tr>
		<td colspan="${param.colspan}"><div align="center">暂无数据……</div></td>
	</tr>
</c:if>