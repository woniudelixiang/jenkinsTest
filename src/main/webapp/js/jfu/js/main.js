

$(function () {  
    //初始化，主要是设置上传参数，以及事件处理方法(回调函数)  
	var uploader = $('#fileupload');
	uploader.fileupload({
        autoUpload: false,//是否自动上传  
        url:"/dao-study/jfujc/toUpload",
        /*formData: {},*/
        dataType: 'json',  
//        fileInput : uploader.find("input:file"),
        acceptFileTypes:  /(\.|\/)(gif|jpe?g|png)$/i,
        maxNumberOfFiles : 6, 
        maxFileSize: 5000000,  // 5 MB
        previewMaxWidth : 60,
        previewMaxHeight : 60,
        previewCrop: false,  // 缩略图是否裁剪
        messages: {
            maxNumberOfFiles: '超过最大数量了',
            acceptFileTypes: '文件格式不正确',
            maxFileSize: '文件太大了',
            minFileSize: '文件太小了'
        },
    });  

	
	 uploader.bind('fileuploadsubmit', function (e, data) {
	    	console.log("^^^^^^^^^^^^^^  fileuploadsubmit ^^^^^^^^^^^^^^");
	    	$("#fileupload").validate();  // 验证表单
	    	if($("#fileupload").valid()){
	    		var formData = uploader.fileupload('option', 'formData');
	    		console.log("formData: " + JSON.stringify(formData));
	    		formData = uploader.serializeJson();
	    		console.log("formData: " + JSON.stringify(formData));
	    		// 禁用提交按键 TODO
	    		return true;  // 上传
	    	}
			data.context.find('button.start').prop('disabled', false); 
//	    	uploader.find("button.start").removeAttr('disabled');
	    	return false;  // 不上传
	     });
	    
	 // 绑定上传完执行的事件
    uploader.bind('fileuploaddone', function(e, data) {
    	// JSON.stringify(data)
    	// 一般用来保存成功后跳转到列表页面     TODO
    	console.log("---------文件上传完毕后的回调函数 ----------" );
//    	 window.location.href="/dao-study/jfujc/main";

    });
    
    uploader.bind('fileuploadfailed', function(e, data) {
    	console.log("--------（取消后调用）-----------");
    	
    });
    
    uploader.bind('fileuploadadd', function(e, data) {
    	// console.log("添加文件后调用的(fileuploadadd)");
    	return true;  // 返回true才能添加进去
    });
    
    uploader.bind('fileuploadadded', function(e, data) {
    	 console.log("添加文件后调用的(fileuploadadded)");
    	return true;  // 返回true才能添加进去
    });
    
    // Load existing files:
	// console.log("----->>>>  "+ $('#fileupload').fileupload('option', 'url'));
    $('#fileupload').addClass('fileupload-processing');
    $.ajax({
        url: "/dao-study/jfujc/load/"+$("#userId").val(),
        dataType: 'json',
        context: $('#fileupload')[0],  // 设置了context后，在回调函数里面使用$(this)即可以获得上下文对象
	    beforeSend:function () {
	    	// showLoading
	    	// console.log("在请求之前调用的函数");
	     },
	     complete:function(XMLHttpRequest, textStatus){
	    	// hideLoading
	    	// console.log( "XMLHttpRequest.responseText" + XMLHttpRequest.responseText); 
	    	// console.log( "textStatus: " + textStatus); 
	     },
	     success:function(data,status){
	    	// console.log( "*****success*******" + status); 
	     },
	     error:function(data,status,e){
	    	// console.log( "*****error*******" + status + "*****" + e);
	     },
    }).always(function () {
    	// 不管加载成功或者失败的回调函数
    	// console.log( "*****always*******"); 
       $(this).removeClass('fileupload-processing');
    }).done(function (result) {
    	// console.log( "*****done*******"); 
    	// 加载完毕的回调函数 
        $(this).fileupload('option', 'done')
            .call(this, $.Event('done'), {result: result});
       
        
        $(".cover").each(function(){
        	$(this).on("click",function(){
        		alert("点我了"+ $(this).val());
        		 $.ajax({
        		        url: "/dao-study/jfujc/setConver/"+ $(this).val(),
        		        dataType: 'json',
        			     success:function(data,status){
        			    	// console.log( "*****success*******" + status); 
        			     },
        			     error:function(data,status,e){
        			    	// console.log( "*****error*******" + status + "*****" + e);
        			     },
        		    });
        	});
        });
    });
  
    
 // 在Firefox环境下测试是，如果将文件数量限制为1，选择一次文件，刷新页面之后文件选择按钮会莫名其妙的被加上一个Disabled属性
    uploader.find("input:file").removeAttr('disabled');
}); 