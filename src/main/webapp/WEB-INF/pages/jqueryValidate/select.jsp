<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bootstrap带搜索筛选多功能下拉框代码</title>
<!-- bootstrap -->
<!-- <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="${ctx }/js/box/BootStrapSelect/css/bootstrap/bootstrap.min.css">

<%-- <script src="${ctx }/js/box/BootStrapSelect/js/modernizr.min.js"></script> --%>

</head>
<body>
	<div class="col-sm-3 ">
		<div class="row">
			<div class="col-sm-8">
				<div id="bts-ex-4" class="selectpicker" data-live="true">
					<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle" >
					<span class="placeholder">请选择</span>
					<span class="caret"></span>
					</button>
					<div class="dropdown-menu">
						<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
							<!-- 搜索 开始-->
							<label class="sr-only" for="input-bts-ex-4">search</label>
							<div class="search-box">
								<div class="input-group">
									<span class="input-group-addon" id="search-icon3">
									<span class="fa fa-search"></span>
									<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">Clear filter</span></a>
									</span>
									<input type="text" placeholder="Search in the list" id="input-bts-ex-4" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
								</div>
							</div>
							<!-- 搜索结束-->
							
							<div class="list-to-filter">
								<!-- 下拉列表数据 -->
								<ul class="list-unstyled">
									<li class="filter-item items" data-filter="item 1" data-value="1">item 1</li>
									<li class="filter-item items" data-filter="item 2" data-value="2">item 2</li>
									<li class="filter-item items" data-filter="item 3" data-value="3">item 3</li>
									<li class="filter-item items" data-filter="item 4" data-value="4">item 4</li>
									<li class="filter-item items" data-filter="item 5" data-value="5">item 5</li>
								</ul>
								
								<!-- 没有搜索到 -->
								<div class="no-search-results">
									<div class="alert alert-warning" role="alert">
										<i class="fa fa-warning margin-right-sm"></i>
										未找到 <strong>'<span></span>'</strong>
									</div>
								</div>
								<!-- 没有搜索到 -->
							</div>
						</div>
					</div>
					<!-- 下拉列表选择的value -->
					<input type="text" name="bts-ex-4" value="">
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx }/js/box/BootStrapSelect/js/jquery-1.11.3.min.js"></script>
	<script src="${ctx }/js/box/BootStrapSelect/js/bootstrap.min.js"></script>
	
	<script src="${ctx }/js/box/BootStrapSelect/js/vendor/tabcomplete.min.js"></script>
	<script src="${ctx }/js/box/BootStrapSelect/js/vendor/livefilter.min.js"></script>
	<script src="${ctx }/js/box/BootStrapSelect/js/vendor/src/bootstrap-select.js"></script>

</body>
</html>