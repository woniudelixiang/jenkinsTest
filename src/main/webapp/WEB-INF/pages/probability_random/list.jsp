<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>











	<script src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
		var gifts = [ {"name" : "mac", "prop" : 1}, 
		              {"name" : "红米", "prop" : 10},
		              {"name" : "u盘", "prop" : 40}, 
		              {"name" : "香皂", "prop" : 49} ];
	
		var gArr = [];
		for (var i = 0; i < gifts.length; i++) {
			gArr.push(gifts[i]['prop'])
		}
		
		console.log(gifts[getResult(gArr)]['name'])

		
		
		
		function getResult(arr) {
			var leng = 0;
			for (var i = 0; i < arr.length; i++) {
				leng += arr[i]; //获取总数
			}

			for (var i = 0; i < arr.length; i++) {
				var random = parseInt(Math.random() * leng); //获取 0-总数 之间的一个随随机整数
				if (random < arr[i]) {
					return i; //如果在当前的概率范围内,得到的就是当前概率
				} else {
					leng -= arr[i]; //否则减去当前的概率范围,进入下一轮循环
				}
			}
		}
	</script>

</body>
</html>