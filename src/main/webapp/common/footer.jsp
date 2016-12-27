<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
<!--
	var basePath = "${pageContext.request.contextPath}";
//-->
</script>
<!-- 格式化日期 -->
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/date/disposeDate.js"></script>

<script type="text/javascript"	src="${pageContext.request.contextPath}/js/ieCommon.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery.js"></script>


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/twitter/bootstrap.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/twitter/bootstrap-modal.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/twitter/bootstrap-tooltip.js"></script>

<%-- <script type="text/javascript"	src="${pageContext.request.contextPath}/js/plugins/validate/jquery.validate.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/plugins/validate/jquery.metadata.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/plugins/validate/messages_cn.js"></script>
 --%>
<!-- 漂亮的表单验证需要导入的js文件 -->
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.metadata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/messages_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/validate-extends.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/ajaxSubmit.jquery.validate.js"></script> 


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/date/WdatePicker.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/hubSpot/build/js/messenger.min.js"></script>

<script type="text/javascript"	src="${pageContext.request.contextPath}/js/autocomplete/jquery.autocomplete.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/fancyBox/source/jquery.fancybox.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/impromptu/jquery-impromptu.js"></script>
<%-- <script type="text/javascript"  src="${pageContext.request.contextPath}/js/grumble.js"></script> --%>
<%-- <script type="text/javascript"  src="${pageContext.request.contextPath}/SweetTooltip/sweet-tooltip.js"></script> --%>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/ajaxfileupload/uploadPreview.min.js"></script>
<!-- <script type="text/javascript"  src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script> -->
<!-- 序列化表单 -->
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/serializeJson.js"></script>
<!-- box操作 -->
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/box/jQuery.checkbox.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/box/jQuery.radio.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/box/jQuery.select.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/select2/js/select2.min.js"></script>
<!-- 提示弹窗 -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Dialog/Dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Dialog/DialogUtils.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.reveal.js"></script>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqueryDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dateFormat.js"></script>
<!-- jQuery countDown插件 -->
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//$("#validateForm").validate();
		
		$(function() {
			var validate = $("#validateForm").validate({
				onsubmit : true, // 是否在提交时验证    true表示验证   false表示不验证
				/* debug: true, */
				//验证不通过时提示信息放置的位置
				errorPlacement : function(error, element) {
					if (element.is(":radio"))
						error.appendTo(element.parent());//放在父元素的后面
					else if (element.is(":checkbox"))
						error.appendTo(element.parent());
					else if (element.is("input[name=captcha]"))
						error.appendTo(element.parent());
					else
						error.insertAfter(element);//放在当前元素的后面
				},
				
				//验证通过时的调用
				success : function(label) {
					label.html("&nbsp;").addClass("right");
				}
			});
		});
		
		 $("button").filter(".historyBackClass").each(function(i) {
			$(this).bind("click", function() {
				// window.history.go(-1);
				history.back();
			});
		}); 
	}); 

</script>

<script type="text/javascript">
	var msg;
	function message(msg) {
		if (msg.length > 0) {
			msg = $.globalMessenger().post({
				message : msg,
				actions : {
					cancel : {
						label : '关闭',
						action : function() {
							return msg.cancel();
						}
					}
				}
			});
		}
	}
	
	function getNowDate(id){
		 var now = new Date();
	     var year = now.getFullYear();       
	     var month = now.getMonth() + 1;    
	     var day = now.getDate();           
	     var strDate = year + "-";
	     if(month < 10)
	    	 strDate += "0";
	     strDate += month + "-";
	     if(day < 10)
	    	 strDate += "0";
	     strDate += day + " ";
		$("#"+id).val(strDate);
	}
	
	function ajaxHandler(url, method, data, dataType, callback) {
		$.ajax({
			type : method,
			data : data,
			url : url,
			dataType : dataType,
			success : callback
		});
	}

	function showDialog(msg) {
		if (msg.length < 1) 
			return;
		$("<div>", {
			id : "inline",
			html : msg,
			css : {
				textAlign : "center",
				width : 500,
				height : 360
			}
		}).appendTo("body");
		$("<a>", {
			id : "showdiv",
			href : "#inline"
		}).appendTo("body");
		$("#showdiv").fancybox();
		$("#showdiv").click();
	}
	
	function prompt(content, title, subId) {
		$.prompt(content, {
			title: title,
			buttons: { "确认": true, "取消": false },
			submit: function(e,v,m,f){
			if(v)
				$("#"+subId).submit();
		}
		});
	}
	
	function showMessage(content, title, url) {
		$.prompt(content, {
			title: title,
			buttons: { "确认": true, "取消": false },
			submit: function(e,v,m,f){
			if(v)
				window.location.href=url;
		}
		});
	}
	
	
	function jumpOther(url){
		window.location.href=url;
	}
	
	function createLoading() {
		var container = $(".container");
		$("<div>")
				.attr("id", "loading")
				.css({
					width : container.width(),
					height : container.height(),
					position : "absolute",
					textAlign : "center",
					zIndex : 100,
					lineHeight : $(window).height() / 12
				})
				.append(
						$("<img src='${pageContext.request.contextPath }/images/loading.gif' />"))
				.prependTo(container);
	}
	
	function examine(url, obj, func, id){
		ajaxHandler(url, "GET", null, "text", function(data) {
			func(data, obj, id);
		});
	}
	function isExamine(url, obj, func){
		$.prompt("确认是否审核？", {
			buttons: { "确认": true, "取消": false },
			submit: function(e,v,m,f){
				if(v) {
					examine(url, obj, func,url.substring(url.lastIndexOf("/")+1));
				}
			}
		});
	}
	
	function examineInSingle(data, obj, id){
		if (data == 1) {
			$(obj).parent().parent().find("td:first").html("<img src='${pageContext.request.contextPath}/images/ico_lock-32.png' width='20' height='20' />");
			$(obj).parent().html("已审核");
		} else {
			message("审核失败！");
		}
	}
	function examineInMany(data, obj) {
		if (data == 1) {
			$(obj).replaceWith("<img src='${pageContext.request.contextPath}/images/ico_lock-16.png' />");
		} else {
			message("审核失败！");
		}
	}
	
	//打开弹窗
	function open_dialog(dialogObject) {
		$(dialogObject.selector).dialog("open");
	};
	
	function bingValidator(formId){
		
		var validate = $("#"+formId).validate({
			onsubmit : true, // 是否在提交时验证    true表示验证   false表示不验证
			/* debug: true, */
			//验证不通过时提示信息放置的位置
			errorPlacement : function(error, element) {
				if (element.is(":radio"))
					error.appendTo(element.parent());//放在父元素的后面
				else if (element.is(":checkbox"))
					error.appendTo(element.parent());
				else if (element.is("input[name=captcha]"))
					error.appendTo(element.parent());
				else
					error.insertAfter(element);//放在当前元素的后面
			},
			
			//验证通过时的调用
			success : function(label) {
				label.html("&nbsp;").addClass("right");
			}
		});
	}
	
</script>
