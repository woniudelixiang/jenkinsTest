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
            <input type="email" name="email" ng-model="vm.entity.email" required="" class="form-control"
                   placeholder="输入邮箱">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>

        <div class="col-sm-10">
            <input required="" ng-pattern="/^[A-Za-z]{1}[0-9A-Za-z_]{1,19}$/" ng-model="vm.entity.name"
                   w5c-unique-check="{url:'http://www.ngnice.com/api/test/user/name/check?name='+vm.entity.name}"
                   class="form-control" name="username" placeholder="输入用户名（输入why520crazy验证存在）">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">密码</label>

        <div class="col-sm-10">
            <input type="password" required="" ng-model="vm.entity.password" name="password"
                   class="form-control" ng-minlength="5" ng-maxlength="15"
                   placeholder="输入密码">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">重复密码</label>

        <div class="col-sm-10">
            <input type="password" required="" w5c-repeat="password" ng-model="vm.entity.repeatPassword"
                   name="repeatPassword"
                   class="form-control"
                   placeholder="重复密码">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">数字</label>

        <div class="col-sm-10">
            <input type="number" required="" ng-model="vm.entity.number" name="number" class="form-control"
                   placeholder="输入数字（10-15）" max="15" min="10">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">自定义验证</label>

        <div class="col-sm-10">
            <!--associate 为关联元素，即关联元素发生变化时触发此处验证-->
            <input type="number" w5c-customizer="vm.customizer()" associate="number" ng-model="vm.entity.customizer" name="customizer" class="form-control"
                   placeholder="数字必须大于上面输入的数字">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">URL</label>

        <div class="col-sm-10">
            <input type="url" required="" ng-model="vm.entity.url" name="url" class="form-control"
                   placeholder="输入URL">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">禁用属性</label>

        <div class="col-sm-10">
            <input type="url" disabled="" ng-model="vm.entity.disabled" name="disabled" class="form-control"
                   placeholder="该属性已经禁用">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">类型</label>

        <div class="col-sm-10">
            <select class="form-control" ng-model="vm.entity.type" required name="type"
                    ng-options="type.text for type in vm.types">
                <option value="">---请选择---</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">多选框</label>

        <div class="col-sm-10">
            <select multiple class="form-control" ng-model="vm.entity.multipleType" required name="multipleType"
                    ng-options="type.text for type in vm.types">
                <option value="">---请选择---</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">动态 Name</label>

        <div class="col-sm-10">
            <input required=""
                   class="form-control" ng-model="vm.entity.dynamicName" w5c-dynamic-name="vm.dynamicName"
                   placeholder="动态 Name">
        </div>
    </div>
    <div class="form-group" ng-if="vm.showDynamicElement">
        <label class="col-sm-2 control-label">动态元素</label>

        <div class="col-sm-10">
            <input required=""
                   class="form-control" w5c-dynamic-element ng-model="vm.entity.dynamic" name="dynamic$123$"
                   placeholder="动态现实元素(name = {name1}${数字}$, 以 {name1}的形式找错误提示信息)">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2">是否可用</label>
        <div class="col-sm-10">
            <label class="radio-inline">
                <input type="radio" ng-model="vm.entity.isAvailable"  value="Y" ng-checked="true"> 可用
            </label>
            <label class="radio-inline">
                <input type="radio" ng-model="vm.entity.isAvailable"  value="N"> 不可用
            </label>
        </div>
    </div>
    <div class="form-group" ng-show="validateForm.$errors.length > 0 && vm.showErrorType == 2">
        <label class="col-sm-2 control-label"></label>

        <div class="col-sm-10">
            <div class="alert alert-danger">
                <ui>
                    <li ng-repeat="error in validateForm.$errors track by $index">
                        {{error}}
                    </li>
                </ui>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="buttom" w5c-form-submit="vm.saveEntity($event)" class="btn btn-success"> 验证</button>
            <button type="buttom" ng-click="validateForm.reset()" class="btn btn-default"> 重置</button>
        </div>

    </div>
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