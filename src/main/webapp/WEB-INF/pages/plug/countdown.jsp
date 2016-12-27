<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery插件之Cookie演示</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<span id = "timeBox"></span>


<!-- http://www.runoob.com/ -->
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<!-- 使用CDN 获取公共的js http://www.bootcdn.cn/ -->

<!-- jQuery cookie插件 -->
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jQuery countDown倒计时插件 -->
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
	
	
	<script type="text/javascript">
		var timeBox = $("#timeBox")
		//开始时间
		var rstartTime = ${startTime};
		//结束时间
		var rendTime = ${endTime};
		//当前系统时间
		var rnow  = ${now};
		
		//开始时间
		var startTime  = new Date(rstartTime);
		//计时事件绑定
		timeBox.countdown(startTime,function(event){
			var format  = event.strftime('倒计时： %D天  %H时  %M分  %S秒');
			$(this).html(format);
		}).on('finish.countdown',function(){
			$(this).html("倒计时已完成");
		});
	
	</script>
</body>
</html>
