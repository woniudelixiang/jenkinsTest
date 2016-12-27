<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>angular-validator演示</title>
	<link href="${ctx }/js/angular/angular-validator/css/css.css" rel="stylesheet">
    <link href="${ctx }/js/angular/angular-validator/lib/highlight/8.0/styles/default.css" rel="stylesheet">
</head>

<body>
<div class="wrap">
    <div class="container" data-ng-controller="validateCtrl" ng-cloak="">
        <form class="form-horizontal w5c-form demo-form" role="form"
              w5c-form-validate="vm.validateOptions" novalidate name="validateForm">
           
            <div class="form-group">
                <label class="col-sm-2 control-label">邮箱</label>

                <div class="col-sm-10">
                    <input type="email" name="email" ng-model="entity.email" required="" class="form-control"
                           placeholder="输入邮箱">
                </div>
            </div>
            
<!--             <div class="form-group"> -->
<!--                 <label class="col-sm-2 control-label">用户名</label> -->

<!--                 <div class="col-sm-10"> -->
<!--                     <input required="" ng-pattern="/^[A-Za-z]{1}[0-9A-Za-z_]{1,19}$/" ng-model="entity.name" -->
<!--                            w5c-unique-check="{url:'http://www.ngnice.com/api/test/user/name/check?name='+entity.name}" -->
<!--                            class="form-control" name="username" placeholder="输入用户名（输入why520crazy验证存在）"> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="form-group"> -->
<!--                 <label class="col-sm-2 control-label">密码</label> -->

<!--                 <div class="col-sm-10"> -->
<!--                     <input type="password" required="" ng-model="entity.password" name="password" -->
<!--                            class="form-control" ng-minlength="5" ng-maxlength="15" -->
<!--                            placeholder="输入密码"> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="form-group"> -->
<!--                 <label class="col-sm-2 control-label">重复密码</label> -->

<!--                 <div class="col-sm-10"> -->
<!--                     <input type="password" required="" w5c-repeat="password" ng-model="entity.repeatPassword" name="repeatPassword" -->
<!--                            class="form-control" -->
<!--                            placeholder="重复密码"> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="form-group"> -->
<!--                 <label class="col-sm-2 control-label">数字</label> -->

<!--                 <div class="col-sm-10"> -->
<!--                     <input type="number" required="" ng-model="entity.number" name="number" class="form-control" -->
<!--                            placeholder="输入数字（10-15）" max="15" min="10"> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="form-group"> -->
<!--                 <label class="col-sm-2 control-label">URL</label> -->

<!--                 <div class="col-sm-10"> -->
<!--                     <input type="url" required="" ng-model="entity.url" name="url" class="form-control" -->
<!--                            placeholder="输入URL"> -->
<!--                 </div> -->
<!--             </div> -->

<!--             <div class="form-group" ng-show="validateForm.$errors.length > 0 && vm.showErrorType == 2"> -->
<!--                 <label class="col-sm-2 control-label"></label> -->

<!--                 <div class="col-sm-10"> -->
<!--                     <div class="alert alert-danger">{{validateForm.$errors[0]}}</div> -->
<!--                 </div> -->
<!--             </div> -->

<!--             <div class="form-group"> -->
<!--                 <div class="col-sm-offset-2 col-sm-10"> -->
<!--                     <button type="buttom" w5c-form-submit="vm.saveEntity()" class="btn btn-success"> 验证</button> -->
<!--                 </div> -->
<!--             </div> -->
        </form>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
                <div class="checkbox">
                    <label>
                        <input ng-model="vm.validateOptions.blurTrig" type="checkbox"> 光标移走触发验证
                    </label>
                </div>
                <select class="form-control" ng-model="vm.showErrorType" ng-change="vm.changeShowType()">
                    <option value="1">每个输入框显示错误信息</option>
                    <option value="2">错误信息集中显示</option>
                </select>
            </div>
        </div>
    </div>
</div>

<script src="${ctx }/js/angular/angular-validator/lib/jquery/1.11.0/jquery.js"></script>
<script src="${ctx }/js/angular/angular-validator/lib/angular/1.2.0/angular.js"></script>
<script src="${ctx }/js/angular/angular-validator/lib/bootstrap/3.0.0/js/bootstrap.js"></script>
<script src="${ctx }/js/angular/angular-validator/lib/angular-ui/ui-bootstrap/0.9.0/ui-bootstrap-tpls.js"></script>
<script src="${ctx }/js/angular/angular-validator/lib/highlight/8.0/highlight.pack.js"></script>
<script src="${ctx }/js/angular/angular-validator/lib/angular-highlightjs/angular-highlightjs.js"></script>
<script src="${ctx }/js/angular/angular-validator/lib/w5cValidator.js"></script>
<script src="${ctx }/js/angular/angular-validator/lib/directive.js"></script>
<script src="${ctx }/js/angular/angular-validator/index.js"></script>

</body>
</html>