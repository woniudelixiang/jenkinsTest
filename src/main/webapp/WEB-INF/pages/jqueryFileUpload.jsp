<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- 
	http://avnpc.com/pages/single-file-upload-component-by-jquery-file-upload 
	
	http://blog.csdn.net/mituan1234567/article/details/41945423
	
	http://www.cnblogs.com/guangshan/p/4534800.html
	
	
	http://www.jqueryfuns.com/resource/1984    //html5多图上传插件 支持拖拽- zyUpload - jQueryfuns
-->
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery File Upload演示</title>
<jsp:include page="/common/css.jsp"/>
</head>
<body>
<div class="container">
    <form id="fileupload" action="${ctx}/jfuc/toUpload" method="POST" enctype="multipart/form-data">
		<input type="text" id="userId" name="userId" value="2"/>
		 用户名：<input type="text" name="username" id="username"/><br><br>
		 密    码：<input type="password" name="password" id="password"/><br><br>
   
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>选择文件..</span>
                    <input type="file" name="files[]" multiple>
                </span>
                <button type="submit" class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>开始上传</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消上传</span>
                </button>
                <button type="button" class="btn btn-danger delete">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" class="toggle">
                <span class="fileupload-process"></span>
            </div>
            <div class="col-lg-5 fileupload-progress fade">
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
    </form>
</div>

<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>

<jsp:include page="/common/js.jsp"/>

<script type="text/javascript">

var uploader = $("#fileupload");  
uploader.fileupload({
    dataType: 'json',  
    autoUpload: false,  
    acceptFileTypes:  /(\.|\/)(gif|jpe?g|png)$/i,
    maxNumberOfFiles : 3, 
    maxFileSize: 5000000,  // 5 MB
    messages: {
        maxNumberOfFiles: '超过最大数量了',
        acceptFileTypes: '文件格式不正确',
        maxFileSize: '文件太大了',
        minFileSize: '文件太小了'
    }
});  
uploader.find("input:file").removeAttr('disabled');  

</script>
</body>
</html>
