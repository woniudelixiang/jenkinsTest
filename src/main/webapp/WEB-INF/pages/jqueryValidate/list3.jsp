<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>将校验规则写到控件的自定义属性中演示</title>
<!-- 验证[通过/不通过]时的提示信息样式 -->
<link type="text/css" href="<c:url value='/js/validate/css/validate.css'/>" rel="stylesheet" />
</head>
<body>
	<form id="myform" action="#" method="post">
		<!-- 该种情况：一般验证用户名是必填的，并且不能与数据库的重复 -->
		<label>用户名：</label>
		<input type="text"  name="username" id="username"  placeholder="请输入用户名" 
			validate="{required:true,remote: { url: '<c:url value="/jqueryValidate/remote"/>', type: 'post'},
				messages:{required:'请输入内容'}}" tip="请输入用户名" />
		<br><br> 
		
		<label>密码：</label>
		<input type="password"  name="password" id="password"  placeholder="请输入密码" 
		tip="请输入用户名"  autocomplete="off"/>
		<br><br> 
		<input type="submit" value="提交验证"/>
	</form>
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<!-- 漂亮的表单验证需要导入的js文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.validate.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/messages_cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/validate-extends.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/ajaxSubmit.jquery.validate.js"></script> 
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/LoggerUtil.js"></script> 
	
	<script type="text/javascript">
	 jQuery.metadata.setType("attr", "validate"); 
		$(function() {
			var validate = $("#myform").validate({
				debug:true,  //调试模式，即使验证成功也不会跳转到目标页面
				onsubmit : true, // 是否在提交时验证        default: true 
				
				// 当键盘按键按下被松开时触发（用该事件来实现表单元素的实时验证）
				onkeyup:function(element){
					$LU.debug($(element).attr("id") + '------触发onkeyup方法实现实时验证-------');
					//$(element).valid();  // 触发校验功能
					this.element( element );   // 触发校验功能
			    	//帮助提示信息移除
			    	removeTip(element);
				},
				
				//指定验证通过/不通过时，提示信息显示的位置
				errorPlacement : function(error, element) {
					$LU.debug($(element).attr("id") + '------触发errorPlacement方法指定验证通过/不通过时，提示信息显示的位置-------');
					placement(error,element);
				}, 
				
				//验证通过时的调用-添加样式
				success : function(label) {
					$LU.debug('------验证通过时的调用-添加样式-------');
					label.html("&nbsp;").addClass("right");
				},
				
			    /*重写校验元素获得焦点后的执行函数--增加[1.光标移入元素时的帮助提示,2.校验元素的高亮显示]两个功能点*/
			    onfocusin: function( element ) {
			    	$LU.debug('------校验元素获得焦点后的执行（onfocusin）-------');
			        this.lastActive = element;
			        // 隐藏改元素的其他验证信息
			        this.addWrapper(this.errorsFor(element)).hide();   
			        /*1.帮助提示功能*/
			        var tip = $(element).attr('tip');
			        if(tip && $(element).parent().children(".tip").length === 0){
			        	var tipD = $("<label class='tip'>" + tip + "</label>");
			        	// 指定提示元素显示的位置
						placement(tipD,element);
			        }
			        /*2.校验元素的高亮显示*/
			        $(element).addClass('highlight');
			    },
			    
			    /*重写校验元素焦点离开时的执行函数--移除[1.添加的帮助提示,2.校验元素的高亮显示]*/
			    onfocusout: function( element ) {
			    	$LU.debug('------校验元素失去焦点后的执行（onfocusout）-------');
			    	/*1.帮助提示信息移除*/
			        removeTip(element);
			        /*2.校验元素高亮样式移除*/
			        $(element).removeClass('highlight');
			        /*3.触发校验功能*/
			        this.element( element );
			    },
			    
			});
			
			
			// 统一管理提示信息的位置
			function placement(newElement,element){
				console.log('指定新增元素显示的位置 ');
				element = $(element);
				if (element.is(":radio"))
					 newElement.appendTo(element.parent());//放在父元素的后面
 				else if (element.is(":checkbox"))
 					newElement.appendTo(element.parent());
 				else if (element.is("input[name=captcha]"))
 					newElement.appendTo(element.parent());
 				else
 					newElement.insertAfter(element);//放在当前元素的后面
			}
			
			// 移除tip提示
			function removeTip(element){
				 $(element).parent().children(".tip").remove();
			}
		});
	</script>
</body>
</html>