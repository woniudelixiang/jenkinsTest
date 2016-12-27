<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>webSocket</title>
</head>
<body>
	
<%-- <script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script> --%>

<script type="text/javascript">
var ws;
function hello(){
	// 1.创建webSocket对象
	ws = new WebSocket("ws://localhost:38080/dao-study/hello");
	
	ws.onopen = function(){
		console.log("websocket 连接成功");	
	};
	
	ws.onmessage = function(event){
		console.log("websocket 收到服务端信息，内容如下："+ event.data);	
	};
	
	ws.onerror = function(){
		console.log("websocket 出错了");
	};
	
	ws.onclose = function(){
		console.log("websocket 关闭");
	};
	
	
};

 hello();

</script>
</body>
</html>