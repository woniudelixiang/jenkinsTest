<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery File Upload演示</title>
<jsp:include page="/common/css.jsp"/>
<style type="text/css">
body{
    font-family: Microsoft Yahei;
    font-size: 15px;
}

fieldset{    width: 680px;    }

legend{    margin-left: 8px;    }

.item{
    height: 56px;
    line-height: 36px;
    margin: 10px;
}

.item .item-label{
    float: left;
    width: 80px;
    text-align: right;
}

.item-text{
    float: left;
    width: 244px;
    height: 16px;
    padding: 9px 25px 9px 5px;
    margin-left: 10px;
    border: 1px solid #ccc;
    overflow: hidden;
}

.item-select{
    float: left;
    height: 34px;
    border: 1px solid #ccc;
    margin-left: 10px;
    font-size: 14px;
    padding: 6px 0px;
}

.item-submit{
    margin-left: 88px;
}

input.error{
    border: 1px solid #E6594E;
}

input.highlight{
    border: 1px solid #7abd54;
}

label.tip{
    margin-left: 5px;
    padding-left: 20px;
    color: #aaa;
    background: url(${ctx}/images/create_new.jpg) no-repeat left center;
}

</style>
</head>
<body>
<div class="container">
    <form id="fileupload" action="" method="POST" enctype="multipart/form-data">
		<input type="text" id="userId" name="userId" value="${imgUser.userId }"/><br>
		用户名：<input type="text" name="username" id="username" value="${imgUser.username }" class="{required:true,minlength:3}" tip="请输入用户名"/><br>
		 密    码：<input type="password" name="password" id="password" value="${imgUser.password }" class="{required:true,minlength:6}" tip="长度为6-16个字符"/><br><br>

        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>选择文件</span>
                    <input type="file" name="files" multiple >
                </span>
                <button type="button" class="btn btn-primary start" name="start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>开始上传</span>
                </button>
                <button type="reset" class="btn btn-warning cancel" name="cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消上传</span>
                </button>
                <button type="button" class="btn btn-danger delete" name="delete">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" class="toggle" name="checkbox">
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
    	<input type="submit" value="提交">
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
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/common/util/LoggerUtil.js"></script> 
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/common/util/CheckUtil.js"></script> 
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/serializeJson.js"></script> 

<script type="text/javascript">
$(function() {
	var validate = $("#fileupload").validate({
		onsubmit : true, // 是否在提交时验证    true表示验证   false表示不验证
		debug: true,
		// 验证不通过时提示信息放置的位置
		errorPlacement : function(error, element) {
			if (element.is(":radio"))
				error.appendTo(element.parent());//放在父元素的后面
			else if (element.is(":checkbox"))
				error.appendTo(element.parent());
			else if (element.is("input[name=captcha]"))
				error.appendTo(element.parent());
			else
				error.insertAfter(element);//放在当前元素的后面
		},
		
		// 验证通过时的调用
		success : function(label) {
			label.html("&nbsp;").addClass("right");
		},
	    
	    /*重写校验元素获得焦点后的执行函数--增加[1.光标移入元素时的帮助提示,2.校验元素的高亮显示]两个功能点*/
	    onfocusin: function( element ) {
	    	// console.log("------------  onfocusin ---------------------");
	        this.lastActive = element;
	        
	        /*1.帮助提示功能*/
	        this.addWrapper(this.errorsFor(element)).hide();
	        var tip = $(element).attr('tip');
	        if(tip && $(element).parent().children(".tip").length === 0){
	        	var tipD = $("<label class='tip'>" + tip + "</label>");
	        	tipD.insertAfter(element); //放在当前元素的后面
	           // $(element).parent().append(tipD);
// 	            if (element.is(":radio"))
// 	            	tipD.appendTo(element.parent());//放在父元素的后面
// 				else if (element.is(":checkbox"))
// 					tipD.appendTo(element.parent());
// 				else if (element.is("input[name=captcha]"))
// 					tipD.appendTo(element.parent());
// 				else
// 					tipD.insertAfter(element);//放在当前元素的后面
	        }
	        
	        /*2.校验元素的高亮显示*/
	        $(element).addClass('highlight');

	        // Hide error label and remove error class on focus if enabled
	        if ( this.settings.focusCleanup ) {
	            if ( this.settings.unhighlight ) {
	                this.settings.unhighlight.call( this, element, this.settings.errorClass, this.settings.validClass );
	            }
	            this.hideThese( this.errorsFor( element ) );
	        }
	    },
	    
	    /*重写校验元素焦点离开时的执行函数--移除[1.添加的帮助提示,2.校验元素的高亮显示]*/
	    onfocusout: function( element ) {
	    	// console.log("------------  onfocusout(焦点离开时的执行) ---------------------");
	    	/*1.帮助提示功能*/
// 	        this.addWrapper(this.errorsFor(element)).hide();
// 	        var tip = $(element).attr('tip');
// 	        if(tip && $(element).parent().children(".tip").length === 0){
// 	            $(element).parent().append("<label class='tip'>" + tip + "</label>");
// 	        }
	    	
	    	/*1.帮助提示信息移除*/
	        $(element).parent().children(".tip").remove();

	        /*2.校验元素高亮样式移除*/
	        $(element).removeClass('highlight');
	        
	        /*3.替换下面注释的原始代码，任何时候光标离开元素都触发校验功能*/
	        this.element( element );
	        
	        /*if ( !this.checkable( element ) && ( element.name in this.submitted || !this.optional( element ) ) ) {
	            this.element( element );
	        }*/
	    },
	    
	});
	
});
	
</script>
</body>
</html>
