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
	function clickme(i) {
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
		}
		ZENG.msgbox.show(tip, i);
	}
	
	function clickhide() {
		ZENG.msgbox._hide();
	}
	
	function clickautohide(i) {
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
		}
		ZENG.msgbox.show(tip, i, 3000);
	}
</script>
</head>

<body>
	<p>4种不同提示图标</p>
	<input type="button" value="图标1" onclick="clickme(1)" />
	<input type="button" value="图标4" onclick="clickme(4)" />
	<input type="button" value="图标5" onclick="clickme(5)" />
	<input type="button" value="图标6" onclick="clickme(6)" />
	<p>隐藏</p>
	<input type="button" value="隐藏" onclick="clickhide()" />
	<p>3秒后自动隐藏</p>
	<input type="button" value="图标1" onclick="clickautohide(1)" />
	<input type="button" value="图标4" onclick="clickautohide(4)" />
	<input type="button" value="图标5" onclick="clickautohide(5)" />
	<input type="button" value="图标6" onclick="clickautohide(6)" />



<!-- 	<div class="zeng_msgbox_layer_wrap" id="q_Msgbox" style="top:100px;display:block"><span class="zeng_msgbox_layer" style="z-index:10000" id="mode_tips_v2"><span class="gtl_ico_hits"></span>您当前没有任何修改<span class="gtl_end"></span></span></div> -->
<!-- 	<div class="zeng_msgbox_layer_wrap" id="q_Msgbox" style="top:200px;display:block"><span class="zeng_msgbox_layer" style="z-index:10000" id="mode_tips_v2"><span class="gtl_ico_fail"></span>服务器出错了<span class="gtl_end"></span></span></div> -->
<!-- 	<div class="zeng_msgbox_layer_wrap" id="q_Msgbox" style="top:300px;display:block"><span class="zeng_msgbox_layer" style="z-index:10000" id="mode_tips_v2"><span class="gtl_ico_succ"></span>恭喜，添加成功！<span class="gtl_end"></span></span></div> -->
	
<!-- 	<div style="top: 400px; display: block" id="q_Msgbox" class="zeng_msgbox_layer_wrap"> -->
<!-- 		<span id="mode_tips_v2" style="z-index: 10000" class="zeng_msgbox_layer"> -->
<!-- 			<span class="gtl_ico_clear"></span> -->
<!-- 			<span class="gtl_ico_loading"></span>正在加载中，请稍后...<span class="gtl_end"></span> -->
<!-- 		</span> -->
<!-- 	</div> -->
	
<!-- 	<div class="zeng_msgbox_layer_wrap" id="q_Msgbox" style="display: block"></div> -->

</body>
</html>
