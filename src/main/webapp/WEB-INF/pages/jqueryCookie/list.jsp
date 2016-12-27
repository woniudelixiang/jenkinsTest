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
<jsp:include page="/common/css.jsp"/>
</head>
<body>

  <p><input type="text" id="username" value="" /></p>
    <p>
        <input type="radio" name="like" value="刘德华" />刘德华
        <input type="radio" name="like" value="张学友" />张学友
    </p>
    <p><input type="button" value="保存" /></p>


<!-- http://www.cnblogs.com/kissdodog/archive/2012/12/14/2818827.html -->
<script src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jqueryCookie/jquery.cookie.js"></script>
<script type="text/javascript">

/* expires:　　有限日期，可以是一个整数或一个日期(单位：天)。　　这个地方也要注意，如果不设置这个东西，浏览器关闭之后此cookie就失效了
   path:　　　    cookie值保存的路径，默认与创建页路径一致。
   domin:　　    cookie域名属性，默认与创建页域名一样。　　这个地方要相当注意，跨域的概念，如果要主域名二级域名有效则要设置　　".xxx.com"
   secrue:　　  一个布尔值，表示传输cookie值时，是否需要一个安全协议。 */


        $(function () {
            $("#username").val($.cookie("username"));
            if ($.cookie("like") == "刘德华") {
                $(":radio[value='刘德华']").attr("checked", 'checked')
            }
            else {
                $(":radio[value='张学友']").attr("checked", 'checked')
            }
            
            $(":button").click(function () {
                $.cookie("username", $("#username").val(), {
                    path: "/", expires: 7
                })
                
                $.cookie("like", $(":radio[checked]").val(), {
                    path: "/", expiress: 7
                })
            })
        });
        
        
        $(function () {
            if ($.cookie("o") == null) {
                var o = { name: "张三", age: 24 };
                var str = JSON.stringify(o);  /* 对序列化成字符串然后存入cookie */
                $.cookie("o", str, {
                    expires:7   //设置时间，如果此处留空，则浏览器关闭此cookie就失效。
                });
                alert("cookie为空");
            } else {
                var str1 = $.cookie("o");
                var o1 = JSON.parse(str1);/* 字符反序列化成对象 */
                alert(o1.name);/* 输反序列化出来的对象的姓名值 */
            }
        });
        
    </script>
</body>
</html>
