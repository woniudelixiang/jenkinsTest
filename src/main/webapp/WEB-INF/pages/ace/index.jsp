<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Bootstrap-ace模板</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="${ctx }/js/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctx }/js/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${ctx }/js/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${ctx }/js/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${ctx }/js/assets/css/ace-skins.min.css" />
</head>

<body>
	<!-- 头部 -->
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small><i class="icon-leaf"></i>Ace Admin</small>
				</a>
			</div>
			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<img class="nav-user-photo" src="${ctx }/js/assets/avatars/user.jpg" alt="Jason's Photo" />
							<span class="user-info">
								<small>Welcome,</small>Jason
							</span>
							<i class="icon-caret-down"></i>
						</a>

						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"><i class="icon-cog"></i>Settings</a></li>
							<li><a href="#"><i class="icon-user"></i>Profile</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="icon-off"></i>Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<!-- 左侧菜单 -->
			<div class="sidebar" id="sidebar">
				<ul class="nav nav-list">
					<li class="active"><a href="index.html"><i class="icon-dashboard"></i><span class="menu-text">控制台</span></a></li>
					<li><a href="typography.html"><i class="icon-text-width"></i><span class="menu-text">文字排版 </span></a></li>
				</ul>
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i><a href="#">Home</a></li>
						<li><a href="#">More Pages</a></li>
						<li class="active">Inbox</li></ul>
				</div>
			</div>
		</div>
	</div>
		
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script src="${ctx }/js/assets/js/ace-extra.min.js"></script>
	<script src="${ctx }/js/assets/js/bootstrap.min.js"></script>
	<script src="${ctx }/js/assets/js/typeahead-bs2.min.js"></script>
	<script src="${ctx }/js/assets/js/bootstrap-tag.min.js"></script>
	<script src="${ctx }/js/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${ctx }/js/assets/js/bootstrap-wysiwyg.min.js"></script>
	<script src="${ctx }/js/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="${ctx }/js/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${ctx }/js/assets/js/jquery.slimscroll.min.js"></script>
	<script src="${ctx }/js/assets/js/ace-elements.min.js"></script>
	<script src="${ctx }/js/assets/js/ace.min.js"></script>
</body>
</html>
