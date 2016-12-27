<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>将校验规则写到控件的自定义属性中演示</title>
 <meta charset="utf-8">
  <meta content="width=device-width" name="viewport">
  <link href="${ctx }/css/iCheck-master/demo/css/custom.css?v=1.0.2" rel="stylesheet">
  <link href="${ctx }/css/iCheck-master/skins/all.css?v=1.0.2" rel="stylesheet">

<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="${ctx }/css/iCheck-master/icheck.js?v=1.0.2"></script>
  <script src="${ctx }/css/iCheck-master/demo/js/custom.min.js?v=1.0.2"></script>
</head>
<body>
   <div class="skin-flat">
        <div class="skin-section">
          <ul class="list">
            <li>
              <input tabindex="13" type="checkbox" id="flat-checkbox-1">
              <label for="flat-checkbox-1">Checkbox 1</label>
            </li>
            <li>
              <input tabindex="14" type="checkbox" id="flat-checkbox-2" checked>
              <label for="flat-checkbox-2">Checkbox 2</label>
            </li>
          </ul>
        </div>
   </div>
  
  
    <div class="skin-flat">
        <div class="skin-section">
          <ul class="list">
            <li>
              <input tabindex="15" type="radio" id="flat-radio-1" name="flat-radio">
              <label for="flat-radio-1">Radio button 1</label>
            </li>
            <li>
              <input tabindex="16" type="radio" id="flat-radio-2" name="flat-radio" checked>
              <label for="flat-radio-2">Radio button 2</label>
            </li>
          </ul>
        </div>
  </div>
  
   <script>
      $(document).ready(function(){
        $('input').iCheck({
          checkboxClass: 'icheckbox_flat-red',
          radioClass: 'iradio_flat-red'
        });
      });
   </script>
</body>
</html>
