<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery文字逐行向上滚动代码</title>
<link href="${ctx }/js/plug/record_top_roll/css/index.css" rel="stylesheet" type="text/css">
<script src="${ctx }/js/plug/record_top_roll/js/jquery.min.js"></script>
</head>

<body>

<!-- -------------摇奖排行榜---------------  -->
<div class="Top_Record">
	<div class="record_Top"><p>摇奖排行榜</p></div>
    <div class="topRec_List">
  		<dl>
        	<dd>编号</dd>
        	<dd>姓名</dd>
        	<dd>奖项</dd>
        	<dd>时间</dd>
        </dl>
        <div class="maquee">
            <ul>
                <li>
                    <div>1</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
                <li>
                    <div>2</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
                <li>
                    <div>3</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
                <li>
                    <div>4</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
                <li>
                    <div>5</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
                <li>
                    <div>6</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
                <li>
                    <div>7</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
                <li>
                    <div>8</div>
                    <div>王**</div>
                    <div>中了30元</div>
                    <div>2014/12/30 14:20</div>
                </li> 
            </ul>
        </div>
    </div>
</div> 


<div class="apple">
	<ul>
		<li><a href="#" target="_blank">你是我的小丫小苹果 </a></li>  
        <li><a href="#" target="_blank">怎么爱你都不嫌多</a></li> 
        <li><a href="#" target="_blank">红红的小脸儿温暖我的心窝 </a></li>  
        <li><a href="#" target="_blank">点亮我生命的火 火火火火</a></li> 
        <li><a href="#" target="_blank">你是我的小丫小苹果 </a></li>  
        <li><a href="#" target="_blank">就像天边最美的云朵</a></li>  
        <li><a href="#" target="_blank">春天又来到了花开满山坡 </a></li>  
        <li><a href="#" target="_blank">种下希望就会收获</a></li> 
    </ul> 
</div>


<script type="text/javascript"> 

  function autoScroll(obj){  
		$(obj).find("ul").animate({  
			marginTop : "-39px"  
		},500,function(){  
			$(this).css({marginTop : "0px"}).find("li:first").appendTo(this);  
		})  
	}
  
	$(function(){  
		setInterval('autoScroll(".maquee")',3000);
		setInterval('autoScroll(".apple")',2000);
		  
	}) 
</script> 
</body>
</html>
