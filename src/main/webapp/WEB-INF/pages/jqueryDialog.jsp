<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jqueryDialog弹窗</title>

<link href="${pageContext.request.contextPath}/css/jqueryDialog/bootstrap.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/jqueryDialog/bootstrap-responsive.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/js/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />

</head>
<body>

<input type="button" value="jqueryDialog弹窗" onclick="open_dialog(dialogObject)"/>

<input type="button" value="jqueryDialog弹窗+iframe" onclick="open_dialog(object)"/>

	<!-- 分配级别弹窗 -->
	<div id="dialogId" class="ui-widget ui-widget-content ui-corner-all">

	</div>

	<div id="dial">
		<iframe id='editFrame' src=''></iframe>
	</div>

	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-ui/jquery-ui-1.10.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jqueryDialog.js"></script>

	<script type="text/javascript">

	var object = $DC.jqueryDialog({
		"dialogId" : "dial",
		"title" : "jqueryDialog弹窗+iframe",
		//当对话框打开后，触发此事件。  
		"open" : function() {
			//此方法一般初始化弹窗的数据
			$("#editFrame").attr('src', "<c:url value='/jfujc/list'/>");
			$("#editFrame").height('250');
			$("#editFrame").width('500');
		},
		// 当对话框关闭之前，触发此事件。如果返回false，则对话框仍然显示。   
		"beforeClose" : function() {
			alert("弹窗关闭之前触发此事件");

			//页面刷新
			window.location.reload();
			return true;
		},

		"buttons" : {
			"确定" : function() {
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
	});

	var dialogObject = $DC.jqueryDialog({
		"dialogId" : "dialogId",
		"title" : "jqueryDialog弹窗",
		//当对话框打开后，触发此事件。  
		"open" : function() {
			//此方法一般初始化弹窗的数据
		},
		// 当对话框关闭之前，触发此事件。如果返回false，则对话框仍然显示。   
		"beforeClose" : function() {
			alert("弹窗关闭之前触发此事件");

			//页面刷新
			window.location.reload();
			return true;
		},

		"buttons" : {
			"确定" : function() {
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
	});

	//打开弹窗
	function open_dialog(dialogObject) {
		$(dialogObject.selector).dialog("open");
	};
</script>

</body>
</html>