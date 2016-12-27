<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>将校验规则写到控件的自定义属性中演示</title>
<!-- 验证[通过/不通过]时的提示信息样式 -->
<link type="text/css" href="${ctx }/js/validate/css/validate.css" rel="stylesheet" />
<!-- iCheck -->
<link href="${ctx }/css/iCheck-master/skins/all.css" rel="stylesheet">

<style type="text/css">
input:-webkit-autofill {
 -webkit-box-shadow: 0 0 0px 1000px white inset;  /* 使用足够大的纯色内阴影覆盖黄色背景 */
 border: 1px solid #CCC!important;
 } 
</style>
<!-- bootstrap -->
<link rel="stylesheet" href="${ctx }/js/box/BootStrapSelect/css/bootstrap/bootstrap.min.css">
<!-- <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">   -->
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">

<style>
.icheckbox_square-blue, .iradio_square-blue { 
   margin: 0;  
   padding: 0;  
   width: 22px; 
   height: 22px;  
   border: none; 
  cursor: pointer; 
}
.radio label, .checkbox label {
    padding-left: 8px;
}

 .form-group .textarea_word { 
 	color: red; 
 	padding: 0 2px;; 
 } 
</style>

</head>
<body>
	<form id="myform" action="#" method="post" class="form-horizontal" >
		<!-- 该种情况：一般验证用户名是必填的，并且不能与数据库的重复 -->
		<div class="form-group">
			<label class="col-sm-1 control-label">用户名：</label>
			<div class="col-sm-2">
				<input type="text" id="username"  name="username"  class="form-control"  placeholder="请输入用户名" tip="用户名只能包含中文,英文和_" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-1 control-label">原始密码：</label>
			<div class="col-sm-2">
				<input type="password"  name="originalPassword" id="originalPassword"  class="form-control" placeholder="请输入密码"  
					tip="请输入密码" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-1 control-label">新密码：</label>
			<div class="col-sm-2">
				<input type="password"  name="password" id="password"  class="form-control" placeholder="请输入密码"  
					tip="请输入密码" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-1 control-label">确认密码：</label>
			<div class="col-sm-2">
				<input type="password"  name="confirmPassword" id="confirmPassword"  class="form-control" placeholder="请输入密码"  
					tip="请输入密码" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-1 control-label">性别：</label>
			<div class="radio radio-inline">
	             <input type="radio" value="0" name="sex">
	             <label>男</label>
	        </div>
	        <div class="radio radio-inline ">
	             <input type="radio" value="1" name="sex">
	             <label>女</label>
	        </div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-1 control-label">爱好：</label>
			<div class="checkbox checkbox-inline">
	            <input type="checkbox"  value="1" name="hobby" >
	            <label>唱歌</label>
	        </div>
	        <div class="checkbox checkbox-inline">
	            <input type="checkbox"  value="2" name="hobby">
	            <label>乒乓球</label>
	        </div>
	        <div class="checkbox checkbox-inline">
	            <input type="checkbox"   value="3" name="hobby">
	            <label>读书</label>
	        </div>
		</div>
		
		<div class="form-group">
			
			<label class="col-sm-1 control-label ">所在地：</label>
			 <div class="col-sm-3 ">
				<div class="col-sm-8">
					<div id="country" class="selectpicker" data-live="true">
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
										<input type="text" placeholder="search" id="input-bts-ex-4" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
									</div>
								</div>
								<!-- 搜索结束-->
								
								<div class="list-to-filter">
									<!-- 下拉列表数据 -->
									<ul class="list-unstyled">
										<li class="filter-item items selected" data-filter="请选择" data-value="-99">请选择</li>
										<li class="filter-item items" data-filter="中国" data-value="1">中国</li>
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
						<input type="hidden" name="country" value="">
					</div>
				</div>
			</div>
			
			 <div class="col-sm-3">
				<div class="col-sm-8">
					<div id="province" class="selectpicker" data-live="true">
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
										<input type="text" placeholder="search" id="input-bts-ex-4" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
									</div>
								</div>
								<!-- 搜索结束-->
								
								<div class="list-to-filter">
									<!-- 下拉列表数据 -->
									<ul class="list-unstyled">
										<li class="filter-item items selected" data-filter="请选择" data-value="-99">请选择</li>
										<li class="filter-item items" data-filter="江苏" data-value="2">江苏</li>
										<li class="filter-item items" data-filter="安徽" data-value="1">安徽</li>
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
						<input type="hidden" name="province" value="">
					</div>
				</div>
			</div>
			
			
			<div class="col-sm-3">
				<div class="col-sm-8">
					<div id="city" class="selectpicker" data-live="true">
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
										<input type="text" placeholder="search" id="input-bts-ex-4" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
									</div>
								</div>
								<!-- 搜索结束-->
								
								<div class="list-to-filter">
									<!-- 下拉列表数据 -->
									<ul class="list-unstyled">
										<li class="filter-item items selected" data-filter="请选择" data-value="-99">请选择</li>
										<li class="filter-item items" data-filter="江苏" data-value="1">南京</li>
										<li class="filter-item items" data-filter="安徽" data-value="2">六安</li>
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
						<input type="hidden" name="city" value="">
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="form-group">
			<label class="col-sm-1 control-label">头像：</label>
			 <div class="col-lg-3">
                <input type="file" name="files" multiple tip="上传的图片只能是 gif|jpg|png">
            </div>
		</div>
        
        
        <div class="form-group">
			<label class="col-sm-1 control-label">备注</label>
			<div class="col-sm-2 textarea">
				<textarea class="form-control" rows="3" cols="2" name="remark" placeholder="备注在1~200字" tip="备注在1~200字" maxlength="3"></textarea>
				<!-- <span class="wordwrap"><var class="word">10</var>/10</span> -->
			</div>
		</div>
		
        <div class="form-group">
			<label class="col-sm-1 control-label">记住我</label>
			<div class="col-sm-2">
				<input type="checkbox" name="rememberMe" id="rememberMe" data-toggle="toggle" data-onstyle="success"  data-on="已记住" data-off="未记住">
			</div>
		</div>
        
		<div class="btn-group"> 
			<button type="button" class="btn btn-primary submitForm">请点击我</button>
		</div>
		
	</form>


	<%-- <script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script> --%>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<!-- 控制台日志工具 -->
	<script type="text/javascript"	src="${ctx }/js/common/util/LoggerUtil.js"></script> 
	<!-- 表单序列化工具 -->
	<script type="text/javascript"	src="${ctx }/js/common/util/FormSerializeUtil.js"></script> 
	<!-- jquery-ajax工具 -->
	<script type="text/javascript"	src="${ctx }/js/common/util/AjaxUtil.js"></script>
	<!-- json工具  -->
	<script type="text/javascript"	src="${ctx }/js/common/util/JsonUtil.js"></script> 
	<!-- 项目（应用）工具  -->
	<script type="text/javascript"	src="${ctx }/js/common/util/ApplicationUtil.js"></script> 
	
	<!-- bootstrap -->
	<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	
	
	
	<!-- 漂亮的表单验证需要导入的js文件 -->
	<script type="text/javascript" src="${ctx }/js/validate/js/jquery.validate.js"></script> 
	<script type="text/javascript" src="${ctx }/js/validate/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/js/messages_cn.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/js/validate-extends.js"></script>
	<!-- jquery-validate表单验证工具 -->
	<script type="text/javascript" src="${ctx }/js/common/util/ValidateUtil.js"></script> 
	
	<!-- bootstrap-toggle -->
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	
	<!-- icheck -->
    <script src="${ctx }/css/iCheck-master/icheck.js"></script>
<%--     <script src="${ctx }/css/iCheck-master/demo/js/custom.min.js"></script> --%>
    
    <!-- bootstrap-select -->
    <script src="${ctx }/js/box/BootStrapSelect/js/vendor/tabcomplete.min.js"></script>
	<script src="${ctx }/js/box/BootStrapSelect/js/vendor/livefilter.min.js"></script>
	<script src="${ctx }/js/box/BootStrapSelect/js/vendor/src/bootstrap-select.js"></script>
    
    <script type="text/javascript" src="${ctx }/js/validate/js/demo1.js"></script>
    
	<script>
      $(document).ready(function(){
        $('input[type="radio"],input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue',
        });
      });
   </script>
	
	
	
	
	<script type="text/javascript">
// 		$VU.initValidator({
// 			formId : 'myform',
// 			submitFormType : 1,  
// 			ajaxSubmitForm : function (form){
// 				var reqJson = $(form).serializeJson(); //将表单序列化为json对象;
// 				$LU.debug("------->>>>>> "+$JU.toString(reqJson));
				
// 				$LU.debug('***************提交逻辑实现');
// 				$AU.ajaxCall({
// 					uri : '${ctx}/jqueryValidate/ajax',
// 					success : function(data, textStatus) {
// 						data = data.datas;
// 						console.log(data[0].username);
// 					},
// 				});
// 			},
// 		});
	</script>

	
</body>
</html>