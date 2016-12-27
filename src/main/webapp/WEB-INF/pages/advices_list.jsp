<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>演示发送手机验证码</title>
<style>
body{background-color: #fff;}
</style>
</head>
<body>
	<input type="button" id="phoneSend" name="phoneSend" class="btn btn-primary" style="border-radius:20px; -webkit-border-radius: 20px;outline:none" onclick='$SD.sendPhoneCode({"id":this.id,"url":"<c:url value='/social/personalCenter/sendPhoneCode/18351821751'/>"})' value="发送手机验证码"></input>
	<br><br>
	<input type="button" id="sendVoiceCode" name="sendVoiceCode" class="btn btn-primary" style="border-radius:20px; -webkit-border-radius: 20px;outline:none" onclick='sendVoiceCode("<c:url value='/social/personalCenter/sendVoiceCode/18351821751'/>")' value="发送语音验证码"></input>
	<br><br>
	<input type="button" id="phoneShorMessage" name="phoneShorMessage" class="btn btn-primary" style="border-radius:20px; -webkit-border-radius: 20px;outline:none" onclick='sendPhoneShorMessage("<c:url value='/social/personalCenter/sendPhoneShorMessage/18351821751'/>")' value="发送手机短信"></input>
	<br><br>
	<input type="button" id="phoneSend" name="phoneSend" class="btn btn-primary" style="border-radius:20px; -webkit-border-radius: 20px;outline:none" onclick='sendMail("<c:url value='/advices/sendMail'/>")' value="发送邮件"></input>
	<br><br>

	<script src="http://libs.baidu.com/jquery/1.4.1/jquery.min.js"></script> 
<%-- <script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery.js"></script> --%>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/ieCommon.js"></script>
<%-- <script type="text/javascript"	src="${pageContext.request.contextPath}/js/wait/waitCommon.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/send/sendCommon.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Dialog/Dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Dialog/DialogUtils.js"></script> --%>

<script type="text/javascript">
function sendPhoneShorMessage(url){
	$IEC.ajaxCall({
		success: function(data){
			var rtype=data.rtype;
			
			if(rtype==1){
				DialogUtils.alert({
					"title" : "提示",
					"icon":"success",
					"msg":"短信发送成功，请注意查收!",
					"width":300,
					"heigth":80,
				});
			}else{
				DialogUtils.alert({
					"title" : "提示",
					"icon":"error",
					"msg":"短信发送失败,请重新发送!",
					"width":300,
					"heigth":80,
				});
			}
		},
        error: function(data){
        	alert("发送短信功能有误!!!"); 
        },
        uri: url,
        type: "GET",
	});
}

function sendVoiceCode(url){
	$IEC.ajaxCall({
		success: function(data){
			var rtype=data.rtype;
			
			if(rtype==1){
				DialogUtils.alert({
					"title" : "提示",
					"icon":"success",
					"msg":"语音验证码发送成功，请注意查收!",
					"width":300,
					"heigth":80,
				});
			}else{
				DialogUtils.alert({
					"title" : "提示",
					"icon":"error",
					"msg":"语音验证码发送失败,请重新发送!",
					"width":300,
					"heigth":80,
				});
			}
		},
        error: function(data){
        	alert("语音验证码功能有误!!!"); 
        },
        uri: url,
        type: "GET",
	});
}

function sendMail(url){
	$IEC.ajaxCall({
		success: function(data){
			var rtype=data.rtype;
			
			if(rtype==1){
				alert("邮件发送成功，请注意查收!");
				/* DialogUtils.alert({
					"title" : "提示",
					"icon":"success",
					"msg":"邮件发送成功，请注意查收!",
					"width":300,
					"heigth":80,
				}); */
			}else{
				alert("邮件发送失败,请重新发送!");
				/* DialogUtils.alert({
					"title" : "提示",
					"icon":"error",
					"msg":"邮件发送失败,请重新发送!",
					"width":300,
					"heigth":80,
				}); */
			}
		},
        error: function(data){
        	alert("邮件发送功能有误!!!"); 
        },
        uri: url,
        type: "GET",
	});
}


</script>




</body>
</html>