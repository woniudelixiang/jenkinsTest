<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ueye" uri="ueye" %>

<html>
	<head>
		<title>菜单</title>
		<style type="text/css">
		<!-- 
			A:link {
				COLOR: #000000; TEXT-DECORATION: none;
			}
			A:visited {
				COLOR: #000000; TEXT-DECORATION: none;
			}
			A:hover {
				COLOR: #339900;
			}
			body {
				background-color: #DEE5FD;
				background-image: url(../images/left_bg.png);
				background-repeat: repeat-y;
				background-repeat: repeat-x;
				
			}
			.ltd{
				font-size:10pt;
				color:#000000;
				text-align:left;
				LINE-HEIGHT: 22px;
				padding-left:3;
				padding-top:0;
				padding-right:0;
				padding-bottom:0;
			}
			.normalCss{
				color: #000000;
				font-weight:normal; 
			}
			
			.checkCss{
				font-weight:bold;
				background: url(../images/abg.png) no-repeat top right;
			}
		 -->
		</style>
	</head>

	<body style="overflow-x:hidden">
		<table border="0" cellspacing="0" cellpadding="0" width="300">
			
			<ueye:forEach items="menus" var="menu" indexed="index">		 		
	 			<tr valign="middle">
	 				 <c:forEach var="row" items="${menu.rows}">
	 				 	<td width="16px">&nbsp;</td>
	 				 </c:forEach>
	 				 <td width="16px" style="width:16px">
	 					<c:choose>
	 						<c:when test="${menu.leaf}">
			 					<img border="0" width="10" height="12" title="叶子节点" src="${pageContext.request.contextPath}/images/blank.gif"/>
	 						</c:when>
	 						<c:when test="${!menu.expanded}">
	 							<a href="${pageContext.request.contextPath }/testMenu/${menu.id}/menu">
			 						<img width="16" height="13" border="0" title="展 开" src="${pageContext.request.contextPath}/images/folder.gif"/>
			 					</a>
	 						</c:when>
	 						<c:when test="${menu.expanded}">
	 							<a href="${pageContext.request.contextPath }/testMenu/${menu.id}/menu">
		 							<img width="16" height="13" border="0" title="折 叠" src="${pageContext.request.contextPath}/images/folder_open.gif"/>
		 						</a>
	 						</c:when>
	 					</c:choose>
	 				 </td>
	 				 
	 				<td colspan="${15-menu.level }" class="ltd" width="${300-menu.level*16 }"> 
	 					<c:choose>
		 					<c:when test="${empty menu.action}">
		 						${menu.label }
		 					</c:when>
		 					<c:otherwise>
		 						<a href="${pageContext.request.contextPath }/${menu.action}" target='mainFrame' onclick="changeCss(this)">
		 							${menu.label }
		 						</a>
		 					</c:otherwise> 
	 					</c:choose>						 					
	 				</td>
	 			</tr>		 		
		 	</ueye:forEach>
		</table>
</body>
	
</html>
