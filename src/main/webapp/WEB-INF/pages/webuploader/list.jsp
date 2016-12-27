<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百度WebUploader演示 </title>
<!-- http://fex.baidu.com/webuploader/doc/index.html   WebUploader API文档 -->
<link rel="stylesheet" type="text/css" href="${ctx }/js/webUploader/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/js/webUploader/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="container">
			<!--头部，相册选择和格式选择-->
			<div id="uploader">
				<div class="queueList">
					<div id="dndArea" class="placeholder">
						<div id="filePicker" ></div>
						<p>或将照片拖到这里，单次最多可选3张</p>
					</div>
				</div>
				
				<div class="statusBar" >
					<div class="progress">
						<span class="text">0%</span> <span class="percentage"></span>
					</div>
					<div class="info" id="info"></div>
					<div class="btns">
						<div id="filePicker2"></div>
						<div class="uploadBtn">开始上传</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/webUploader/webuploader.js"></script>
	<%-- <script type="text/javascript" src="${ctx }/js/webUploader/upload.js"></script> --%>
	<script type="text/javascript" src="${ctx }/js/webUploader/upload1.js"></script>
</body>
</html>