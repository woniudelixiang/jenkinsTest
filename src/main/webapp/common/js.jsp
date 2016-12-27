<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- The template to display files available for upload 文件预览模板-->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start。。。</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download  上传后文件回调显示模板 -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="${pageContext.request.contextPath}/{%=file.url%}/{%=file.id%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img  style="width:30px;heigth:30px;" src="${pageContext.request.contextPath}/{%=file.thumbnailUrl%}/{%=file.id%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="${pageContext.request.contextPath}/{%=file.url%}/{%=file.id%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">错误</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>

            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="${pageContext.request.contextPath}/{%=file.deleteUrl%}/{%=file.id%}" {% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
				<input type="radio" name="cover" class="cover" value="{%=file.id%}"  {%=file.cover ==1 ? 'checked':''%} > 
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}


</script>

<!-- <script src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script> -->
<script type="text/javascript"src="${ctx }/js/jquery-1.7.2.js"></script>

<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="${ctx}/js/jfu/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="//blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="//blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="//blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<!-- <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<!-- blueimp Gallery script -->
<script src="//blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="${ctx}/js/jfu/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="${ctx}/js/jfu/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="${ctx}/js/jfu/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="${ctx}/js/jfu/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<%-- <script src="${ctx}/js/jfu/js/jquery.fileupload-audio.js"></script> --%>
<!-- The File Upload video preview plugin -->
<%-- <script src="${ctx}/js/jfu/js/jquery.fileupload-video.js"></script> --%>
<!-- The File Upload validation plugin -->
<script src="${ctx}/js/jfu/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="${ctx}/js/jfu/js/jquery.fileupload-ui.js"></script>
<!-- The main application script -->
<script src="${ctx}/js/jfu/js/main.js"></script>
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="${ctx}/js/jfu/js/cors/jquery.xdr-transport.js"></script>
<![endif]-->



<!-- 漂亮的表单验证需要导入的js文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/jquery.metadata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/messages_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate/js/validate-extends.js"></script>
<%-- <script type="text/javascript"	src="${pageContext.request.contextPath}/js/validate/js/ajaxSubmit.jquery.validate.js"></script>  --%>

