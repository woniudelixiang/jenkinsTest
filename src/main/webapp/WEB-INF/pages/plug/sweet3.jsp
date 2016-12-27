<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SweetAlert2对话框</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Courgette">
<link rel="stylesheet" href="${ctx }/js/plug/sweet/example/example.css">
<link rel="stylesheet" href="${ctx }/js/plug/sweet/example/buttons.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<!-- <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script> -->
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
<script src="${ctx }/js/plug/sweet/dist/sweetalert2.min.js"></script>
<link rel="stylesheet" href="${ctx }/js/plug/sweet/dist/sweetalert2.min.css">

<!-- IE support -->
<script
	src="https://cdn.jsdelivr.net/es6-promise/latest/es6-promise.min.js"></script>

<!-- Promise.finally support -->
<script src="https://cdn.jsdelivr.net/promise.prototype.finally/1.0.1/finally.js"></script>

<script src="${ctx }/js/plug/sweet/sweetCommon.js"></script>

<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>

<body>
	<button onclick="tanF()">自动关闭的对话框</button>
	<script type="text/javascript">
		function tanF(){
			 $SC.swal({
				title :"自动关闭的对话框&rarr;",
			    showConfirmButton: true,
			    confirmButtonText: 'Great!',
			    allowOutsideClick : true,   //	如果设置为“true”，用户可以通过点击警告框以外的区域关闭警告框。
			    showLoaderOnConfirm: true,
			    buttonsStyling :true,
// 			      preConfirm : function() {
// 	 			        return new Promise(function(resolve) {
	 			        	
// 	 			           setTimeout(function() {
// 	 			            resolve();
// 	 			          }, 2000); 
// 	 			        });
// 				      },
			}); 

		};
	</script>
</body>
</html>