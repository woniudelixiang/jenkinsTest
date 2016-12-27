<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajaxFileUpload</title>
</head>
<body>

	 <input id="fileToUpload" type="file" name="fileToUpload" multiple>
     <button id="buttonUpload">上传</button>
	
	<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${ctx }/js/ajaxfileupload.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#buttonUpload").click(function(){
				var data = { name: 'my name', description: 'short description' } 
			    $.ajaxFileUpload({
			        url: '${ctx }/ajaxFileUpload/upload',
			        secureuri: false,
			        data: data,
			        fileElementId: 'fileToUpload',
			        dataType: 'json',
			        success: function (data) {
			        	//data = JSON.parse(data)
			            alert(data.message);
			        },
			        error: function (data) {
			            alert("error");
			        }
			    });
				
			});
		});
	
	</script>
</body>
</html>