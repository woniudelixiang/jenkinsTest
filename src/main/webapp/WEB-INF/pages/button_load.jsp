<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>按钮加载效果</title>
		<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="${ctx }/js/button_load/dist/ladda-themeless.min.css">
		<link rel="stylesheet" href="${ctx }/js/button_load/css/prism.css">
</head>
<body>
	<div class="container">
			<p/>
			<div class="row">
				<div class="col-md-6 col-md-offset-3 text-center">
					<p>
						<button class="btn btn-primary ladda-button" data-style="zoom-in"><span class="ladda-label">zoom-in</span></button>
						<button class="btn btn-primary ladda-button" data-style="zoom-out"><span class="ladda-label">zoom-out</span></button>
					</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6 col-md-offset-3 text-center">
					<p>
						<button class="btn btn-primary ladda-button" data-style="slide-left"><span class="ladda-label">slide-left</span></button>

						<button class="btn btn-primary ladda-button" data-style="slide-right"><span class="ladda-label">slide-right</span></button>

						<button class="btn btn-primary ladda-button" data-style="slide-up"><span class="ladda-label">slide-up</span></button>

						<button class="btn btn-primary ladda-button" data-style="slide-down"><span class="ladda-label">slide-down</span></button>
				</div>
			</div>
		</div>


	<a href="#" id="form-submit" class="btn btn-primary btn-lg ladda-button" data-style="expand-right" data-size="l">
	    <span class="ladda-label">Submit form</span>
	</a>
		<script src="http://libs.baidu.com/jquery/1.4.1/jquery.min.js"></script>
		<script src="${ctx }/js/button_load/dist/spin.min.js"></script>
		<script src="${ctx }/js/button_load/dist/ladda.min.js"></script>

		<script>
			// Bind normal buttons
			Ladda.bind( 'div button', { timeout: 2000 } );
			
			
			var data={"id":1,"username":"wqj"};
			var url = "${ctx }/buttonLoad/submitForm";
			$(function() {
				 $('#form-submit').click(function(e){
				   e.preventDefault();
				   var l = Ladda.create(this);
				   l.start();  //加载开始
				   $.post(url,{ data : data },
				     function(response){
				       console.log(response);
				       //l.isLoading() 判断是否在加载
				       l.stop();    //加载停止
				     }, "json");
				 });
			});
			
			
		</script>
</body>
</html>