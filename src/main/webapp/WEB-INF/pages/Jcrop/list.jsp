<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery插件之Cookie演示</title>
<!-- 1 -->
<link rel="stylesheet" href="css/jquery.Jcrop.css">
</head>

<body>
	<div class="main">
		<div class="inwrap">
			<div class="example">
				<img src="img/11.jpg" id="cropbox">
			</div>
			<div class="example">
				<form action="/CutImage/ImageCropperServlet" method="post"
					onsubmit="return checkCoords();">
					<input type="hidden" id="imgUrl" name="imgUrl" value="/img/11.jpg">
					<label>X1 <input type="text" size="4" id="x1" name="x" />
					</label> <label>Y1 <input type="text" size="4" id="y1" name="y" />
					</label> <label>X2 <input type="text" size="4" id="x2" name="x2" />
					</label> <label>Y2 <input type="text" size="4" id="y2" name="y2" />
					</label> <label>W <input type="text" size="4" id="w" name="w" />
					</label> <label>H <input type="text" size="4" id="h" name="h" />
					</label>
					<!-- 
					<input type="hidden" id="x" name="x">
					<input type="hidden" id="y" name="y">
					<input type="hidden" id="w" name="w"> 
					<input type="hidden" id="h" name="h">  -->
					<input class="cxbtn" type="submit" value="裁剪图像">
				</form>
			</div>
		</div>
	</div>
	<!-- 2 -->
<script src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jcrop/js/jquery.Jcrop.min.js"></script>

	<script>
		$('#cropbox').Jcrop({
	        onChange: showCoords,   // 选框选定时的事件
	        onSelect: showCoords,  // 选框改变时的事件
	        aspectRatio : 1,       // 设置选框宽高比,即 width/height
		}); 
	
		function updateCoords(c) {
			$('#x').val(c.x);
			$('#y').val(c.y);
			$('#w').val(c.w);
			$('#h').val(c.h);
		};

		function checkCoords() {
			if (parseInt($('#w').val())) {
				return true;
			};
			alert('请先选择要裁剪的区域后，再提交。');
			return false;
		};

		function showCoords(c) {
			$('#x1').val(c.x);
			$('#y1').val(c.y);
			$('#x2').val(c.x2);
			$('#y2').val(c.y2);
			$('#w').val(c.w);
			$('#h').val(c.h);
		};
		function clearCoords() {
			$('#cropbox input').val('');
			$('#h').css({
				color : 'red'
			});
			window.setTimeout(function() {
				$('#h').css({
					color : 'inherit'
				});
			}, 500);
		};
	</script>

</body>
</html>


