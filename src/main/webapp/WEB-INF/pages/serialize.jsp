<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>表单序列化</title>
</head>
<body>
	<form id="searchForm">
<!--         <input type="hidden"  name="user.country.id" value="1"/> -->
<!--         <input type="hidden"  name="user.country.countryName" value="2"/> -->
        
        <input type="hidden"  name="name" value="clx"/>
        <input type="hidden"  name="name" value="wqj"/>
        
<!--         <input type="hidden"  name="user.id" value="1"/> -->
<!--         <input type="hidden"  name="user.userName" value="2"/> -->
<!--         <input type="hidden" name="password" value="2"/> -->
    </form>
<!-- 	<script src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script> -->
	<script type="text/javascript"	src="${ctx}/js/jquery.js"></script>
	<script src="${ctx }/js/common/util/CheckUtil.js"></script>
	<script src="${ctx }/js/common/util/LoggerUtil.js"></script>
	<script src="${ctx }/js/serializeJson.js"></script>
	<script src="${ctx }/js/common/util/AjaxUtil.js"></script>
	
	
	<script type="text/javascript">
// 		$LU.openDebug(false);
// 		var reqJson = $('#searchForm').serializeJson();
// 		$LU.debug("序列化结果为 : " + JSON.stringify(reqJson));
	var reqJson =[{"id":1, "username":"wqj"},{"id":2, "username":"clx"},{"id":3, "username":"wr"}];
	$AU.ajaxCall({
		"uri" : "${ctx }/plug/paramBading",    
		"type" : "POST",    
		"data" : reqJson,
		"success" : function(data, textStatus){
			console.log("textStatus: " + textStatus );
		}, 
		
	});

	</script>
</body>
</html>
