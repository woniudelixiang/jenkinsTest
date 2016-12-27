<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style= "margin: 0; padding: 0;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jquery 的BlockUI插件</title>

</head>
<body style= "margin: 0; padding: 0;">

	<!-- BEGIN PAGE -->
	<div class="page-content" id="page_content"
		style=" ">
	</div>
	<!-- END PAGE -->

	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${ctx }/js/jqueryBlockUI/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${ctx }/js/jqueryBlockUI/loading.js"></script>

	<script type="text/javascript">
	
	
	    
	/*  $(function(){
		 
		 $(el).block({
			 "message" : '<img src="/dao-study/js/jqueryBlockUI/loading3.gif" />',
         	"fadeIn": 300,    // 淡入
         	"fadeOut": 300,   // 淡出
         	
         	// 提示框的样式设置
         	"css" : { backgroundColor: 'none', 
         		      color: 'none',
         		      borderColor:'none',
         		      borderWidth:'0px'      // 无边框
         	         },
         	 
         	 // 遮光罩的样式设置
     	     "overlayCSS" : { backgroundColor: '#000',   // 背景颜色为黑色
     	    	 			  opacity: 0.3,              // 透明度  值越大越不透明，越小越透明
     	    	 			  cursor: 'wait'             // 鼠标的样式 
     	     				},
			
		 }
		 );
		 
		 
	 }); */
	 var el = $(".page-content");
	 
         $(function() {
        	$('#Button').click(function() {
        		/*  $(".page-content").css({
        			 "height" : $(document).height(),
        			 "width" : $(document).width(),
        			 "position" : 'absolute',
        		 }); */
        		 
        		
        		
        		// 打开弹出层
        		$LD.blockUI({
        			"el":el
        		}); 
        		 // 关闭弹出层
                setTimeout('$LD.unblockUI({"el":el})', 2000);
        	});
            //简单的气泡提示
            // $.growlUI('提示', '删除成功!');
        }); 
       
    </script>
</head>
<body>
	<ol>
		<li><input id="Button" type="button" value="页面加载效果" /></li>
	</ol>
</body>
</html>