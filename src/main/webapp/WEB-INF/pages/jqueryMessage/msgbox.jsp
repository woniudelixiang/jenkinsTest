<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一款腾讯UED设计的提示插件</title>
<link rel="stylesheet" href="${ctx }/js/jqueryMessage/css/msgbox.css" />
<script type="text/javascript" src="${ctx }/js/jqueryMessage/msgbox.js"></script>
<script>
	function showMsgbox(i, timeout) {
		var tip = "";
		switch (i) {
			case 1:
				tip = "服务器繁忙，请稍后再试。";
				break;
			case 4:
				tip = "设置成功！";
				break;
			case 5:
				tip = "数据拉取失败";
				break;
			case 6:
				tip = "正在加载中，请稍后...";
				break;
			default:
				i = 0;
		}
		
		ZENG.msgbox.show(tip, i, timeout);
	}

	function hideMsgbox() {
		ZENG.msgbox._hide();
	}
</script>
</head>

<body>
	<p>4种不同提示图标</p>
	<input type="button" value="hint 1" onclick="showMsgbox(1,1000)" />
	<input type="button" value="success 4" onclick="showMsgbox(4)" />
	<input type="button" value="fail 5" onclick="showMsgbox(5)" />
	<input type="button" value="loading 6" onclick="showMsgbox(6)" />
	<p>隐藏</p>
	<input type="button" value="隐藏" onclick="hideMsgbox()" />
</body>
</html>
