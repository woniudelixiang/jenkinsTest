<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script> -->

	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${ctx }/js/jqueryMessage/jquery.messager.js"></script>

<%-- 	<script type="text/javascript" src="${ctx }/js/angular/angular.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="${ctx }/js/angular/app.js"></script> --%>
<%-- 	<script type="text/javascript" src="${ctx }/js/angular/controller.js"></script> --%>


<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body>

	<!-- ng-app 指令告诉 AngularJS，<div> 元素是 AngularJS 应用程序 的"所有者"。 -->
	<!-- ng-model 指令把输入域的值绑定到应用程序变量 name。 -->
	<!-- ng-bind 指令把应用程序变量 name 绑定到某个段落的 innerHTML。 -->
	<!--  ng-init 指令初始化 AngularJS 应用程序变量。 -->

	<!-- angular.module  定义应用 -->
	<!-- controller 控制器控制应用 -->






	<!-- 	<div ng-app="" ng-init="name='John'">   -->
	<!-- 		<p>  -->
	<!-- 			名字 : <input type="text" ng-model="name">    -->
	<!-- 		</p> -->
	<!-- 		<h1>Hello {{name}}</h1> -->
	<!-- 	</div> -->


	<!-- 	<div data-ng-app="" data-ng-init="firstName='John'"> -->
	<!-- 		<p>姓名为 <span data-ng-bind="firstName"></span></p> -->
	<!-- 	</div> -->

	<!-- 	<div ng-app="" ng-init="name='John'"> -->
	<!-- 		<p>姓名为 <span ng-bind="name"></span></p> -->
	<!-- 	</div> -->

<!-- 	<div ng-app="myApp" ng-controller="myCtrl"> -->
<!-- 		名: <input type="text" ng-model="firstName"><br>  -->
<!-- 		姓: <input type="text" ng-model="lastName"><br><br>  -->
<!-- 		姓名: {{firstName + " " + lastName}} -->
<!-- 	</div> -->

	<script>
// 		var app = angular.module('myApp', []);
// 		app.controller('myCtrl', function($scope) {
// 			$scope.firstName = "John";
// 			$scope.lastName = "Doe";
// 		});
	</script>
</body>

</html>