<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码强度检测演示</title>
<link rel="stylesheet" type="text/css" href="${ctx }/js/passwordStrength/style.css" /> 
</head>
<body>
	
  <div class="demo">
    <p>
      <label>请输入密码：</label>
      <input type="password" id="pass" class="input">
    </p>
    <div id="passwordStrengthDiv" class="is0"></div>
    <p>
      <label>确认密码：</label>
      <input type="password" id="repass" class="input">
    </p>
  </div> 
  
  
  <input type="text" id="goAfter"/>
  <input type="text" id="after"/>
 
 	<!-- <p data-x="one">Text one</p>
	<p data-x="two">Text two</p>
	<p data-x="three">Text three</p>
	<p data-x="four">Text four</p> -->
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx }/js/passwordStrength/jquery.passwordStrength.js"></script>

<script type="text/javascript">
$.fn.goAfter = function(options){
	
	return this.each(function(){
		 $(this).keyup(function(){
			 $("#after").val(this.value);
		});	
	});
}

$(function(){
	 $('#goAfter').goAfter();
});
</script>

<script type="text/javascript">

//var $this = $('p').mangle(); 
 //alert("$this[0] : " + $($this[0]).data('x')+"  $this[1] : " +  $($this[1]).data('x') +"   $this[2] : "+ $($this[2]).data('x') +  "  $this[3] : "+$($this[3]).data('x'));
 
//var $this2 = $('p').mangle2(); 
 //alert("$this2[0] : " + $($this2[0]).data('x')+"  $this2[1] : " +  $($this2[1]).data('x') +"   $this2[2] : "+ $($this2[2]).data('x') +  "  $this2[3] : "+$($this2[3]).data('x'));

// 结果看出:没有使用each的时候是对整体的元素批量操作，使用啦each时候可以分别对元素进行单独操作

$(function(){
	$('#pass').passwordStrength();
});
</script>

</body>
</html>