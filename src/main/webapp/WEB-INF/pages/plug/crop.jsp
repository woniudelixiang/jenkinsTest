<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片左右旋转在线裁剪jquery插件</title>

<link rel="stylesheet" type="text/css" href="${ctx }/js/plug/crop/imgareaselect-default.css" />

<style type="text/css">
#picture {
	overflow: hidden;
	position: relative;
	height: auto;
	/* width: 280px; */
	margin: 0 auto;
}
.portrait_left {
	float: left;
	height: auto;
	width: 306px;
}

.img_preview {
	border: 1px solid #ccc;
	border-radius: 100%;
	overflow: hidden;
	position: relative;
	height: 100px;
	margin: 0 auto;
	width: 100px;
}

</style>

</head>
<body>
	<div class="portrait_left">
		<%-- <img id="avatar" alt="请上传头像" src="${ctx }/images/seniorshow.png"> --%>
		<div class="upldFiv">
			<div id="imgdiv" class="imgdiv">
				<img id="avatar"/>
			</div>
			<div>
				头像： <input name="file" id="file" type="file">
			</div>
		</div>
		
		
		<!--通过生成尺寸和旋转角度 后台获取尺寸和旋转角度再进行裁剪-->
		<form id="crop_form" method="post" action=".">
			<input id="leftTopXaxis" type="text" name="leftTopXaxis">             <!-- 左上角的横坐标 -->
			<input id="leftTopYaxis" type="text" name="leftTopYaxis">             <!-- 左上角的纵坐标 -->
			<input id="rightBottomXaxis" type="text" name="rightBottomXaxis">     <!-- 右下角的横坐标 -->
			<input id="rightBottomYaxis" type="text" name="rightBottomYaxis">     <!-- 右下角的纵坐标 -->
			<input id="cropWidth" type="text" name="cropWidth">                   <!-- 左上角的横坐标 -->
			<input id="cropHeight" type="text" name="cropHeight">                 <!-- 左上角的横坐标 -->
			<input id="rotation" type="text" name="rotation">                     <!-- 左上角的横坐标 -->
		</form>
		
		<div>
			<a href="javascript:;"  onClick="avatarrotateleft();">向左旋转</a> 
			<a href="javascript:;"  onClick="avatarrotateright();">向右旋转</a>
			<button onClick="submit_avatar();">确定</button>
		</div>
	</div>


	<div class="portrait_right">
		<div class="portrait_right_bottom" >
			<div class="portrait1">
				<div id="img_preview" class="img_preview">
					<img id="avatar1" alt="头像预览" src="${ctx }/images/seniorshow.png"  />
				</div>
				<p>大尺寸头像，180×180</p>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${ctx }/js/plug/crop/jquery.imgareaselect.pack.js"></script>
	<script type="text/javascript" src="${ctx }/js/plug/crop/jQueryRotate.js"></script>
	<script type="text/javascript" src="${ctx }/js/uploadPreview.min.js"></script>
	

	<script type="text/javascript">
		$(function() {
			new uploadPreview({
				UpBtn : "file",       // id
				DivShow : "imgdiv",   // id
				ImgShow : "avatar"    // 图片的id
			});
		});
	</script>


	<script type="text/javascript">
		$(document).ready(
				function() {
					function adjust(img, el, selection) {
						var scaleX = $(el).width() / (selection.width || 1);
						var scaleY = $(el).height() / (selection.width || 1);
						var imgId = $(img).attr("id");
						
						$(el + ' img').css({
									width : Math.round(scaleX * $('#' + imgId).width()) + 'px',
									height : Math.round(scaleY * $('#' + imgId).height()) + 'px',
									marginLeft : '-' + Math.round(scaleX * selection.x1) + 'px',
									marginTop : '-' + Math.round(scaleY * selection.y1) + 'px',
								});
					}
					
					function preview(img, selection) {
						adjust(img,'#img_preview', selection);
					}
					
		           $('img#avatar').imgAreaSelect({
						instance: true,        // 返回插件引用
						aspectRatio : "1:1",
						handles: true,   // 显示改变框
						persistent: true,  // 让用户只能移动/缩放选择区域
						movable : true,  // 设置是否支持选择框移动
						x1 : 5,   // 左上角的横坐标
						y1 : 5,   // 左上角的纵坐标
						x2 : 100,  // 右下角的横坐标
						y2 : 100,  // 右下角的纵坐标
						onSelectEnd : function(img, selection) {  // 选择结束时所调用的函数    第一个选项是这个插件所应用图像的引用，另外一个则是呈现当前选择的对象。
							$('#leftTopXaxis').val(selection.y1);
							$('#leftTopYaxis').val(selection.x1);
							$('#rightBottomXaxis').val(selection.x2);
							$('#rightBottomYaxis').val(selection.y2);
							$('#cropWidth').val(selection.width);
							$('#cropHeight').val(selection.height);
						},
						onSelectChange : preview,  // 改变选择区域时所调用的函数
						onInit : cropFormInit,     // 插件初始化时所调用的函数
					}); 
				});
			
		function cropFormInit(img, selection){
			$('#leftTopXaxis').val(selection.y1);
			$('#leftTopYaxis').val(selection.x1);
			$('#rightBottomXaxis').val(selection.x2);
			$('#rightBottomYaxis').val(selection.y2);
			$('#cropWidth').val(selection.width);
			$('#cropHeight').val(selection.height);
			$('#rotation').val(0);
		}
		
		function avatarrotateleft() {
			var value = parseInt($('#rotation').val()) - 90;
			$('#avatar').rotate({
				animateTo : value
			});
			$('#avatar1').rotate({
				animateTo : value
			});
			$('#avatar2').rotate({
				animateTo : value
			});
			$('#rotation').val(value);
		}
		
		function avatarrotateright() {
			value += 90;
			var value = parseInt($('#rotation').val()) + 90;
			$('#avatar').rotate({
				animateTo : value
			});
			$('#avatar1').rotate({
				animateTo : value
			});
			$('#avatar2').rotate({
				animateTo : value
			});
			$('#rotation').val(value);
		}
	</script>

</body>
</html>