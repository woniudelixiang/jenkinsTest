$(function(){

	/*第一步：编写验证规则和提示信息*/
	$VU.setRules({
		// 用户名
		username:{
			required:true,
			remote: { 
				url: $AppU.getBasePath() + '/jqueryValidate/usernameIsExist',  //后台处理程序地址
				type: 'post',  //请求发送方式
				dataType: "json",  //接受数据格式   
				data: { //要传递的数据
					username: function() {
						return $("#username").val();
					}
				},
			},
		},

		// 原始密码
		originalPassword:{
			required:function(){
				if($('#password').val() || $('#confirmPassword').val()){
					return true;   // 如果新密码或者确认密码填写了，那么原始密码必须填写
				}else{
					return false;  // 否则不是必填项
				}
			},

			remote: { 
				url: $AppU.getBasePath() + '/jqueryValidate/passwordIsRight', 
				type: 'post', 
				dataType: "json", 
				data: {
					userId : 1,
					password : function() {
						return $("#originalPassword").val();
					},
				},
			},
		},

		// 新密码
		password:{
			required:function(){
				if($('#originalPassword').val() || $('#confirmPassword').val()){
					return true;  // 如果原始密码或者确认密码填写了，那么原始密码必须填写
				}else{
					return false;  // 否则不是必填项
				}
			}, 
			rangelength:[5,10]
		},

		// 确认密码
		confirmPassword:{
			required:function(){
				if($('#originalPassword').val() || $('#password').val()){
					return true;  // 如果原始密码或者新密码填写了，那么原始密码必须填写
				}else{
					return false;  // 否则不是必填项
				}}, 
				equalToOnNotEmpty:'#password', 
		},
		
		sex :{
			required:true,
		},
		
		hobby :{
			required:true,
			minlength : 2,
		},

		country : {
			extRequired : true,
		},
		
		province : {
			extRequired : true,
			
		},
		
		city : {
			extRequired : true,
			
		},
		
		remark : {
			required:true,
		},
		
		files : {
			required:true,
			accept : 'gif|jpg|png',
		},
	});


	$VU.setMessages({
		username:{
			required:'用户名必填',
			remote: '用户名已存在',
		},
		// 原始密码
		originalPassword:{
			required:'原始密码必填',
			remote: '原始密码不正确',
		},

		// 新密码
		password:{
			required:'新密码必填',
			rangelength: '密码长度必须为{0}到 {1}位',
		},

		// 确认密码
		confirmPassword:{
			required:'再次确认必填',
			rangelength: '密码长度必须为{0}到 {1}位',
			equalToOnNotEmpty :'两次输入不一致',
		},
		
		sex :{
			required:'性别必选',
		},
		
		hobby :{
			required:'必选',
			minlength : '至少选{0}项'
		},
		
		country : {
			extRequired : '必选',
			
		},
		province : {
			extRequired : '必选',
			
		},
		city : {
			extRequired : '必选',
			
		},
		
		remark : {
			required : '必填',
		},

		files : {
			required:"必选",
			accept : '上传的图片只能是 gif|jpg|png',
		},
	});

	
	// 统一管理提示信息的位置
	$VU.setPlacement({
		placement : function (newElement,element){
			console.log('指定新增元素显示的位置 ....');
			element = $(element);
			if (element.is(":radio")){
				console.log('radio ....');
				newElement.appendTo(element.parent().parent().parent());//放在父元素的后面
			}
			else if (element.is(":checkbox"))
				newElement.appendTo(element.parent().parent().parent());//放在父元素的后面
			else if (element.is("input[name=captcha]"))
				newElement.appendTo(element.parent().parent());
			else
				newElement.insertAfter(element.parent());// 放在当前元素父元素的的后面
		},
	});

	/*第二步：编写验证规则和提示信息*/
	$VU.initValidator({
		formId : 'myform', 
		meta : 'validate', 
	});

	$('#myform .submitForm').click(function(){
		$('#myform').submit();  // 触发校验功能
	});

	
	
	
	
	inputNum($(".form-group textarea"));
    
    /*
     * 剩余字数统计
     * 注意 最大字数只需要在 textarea的maxlength="3"属性中
     */
    function inputNum(textArea) {
        var maxlength = $(textArea).attr('maxlength');
        if(maxlength){
        	var curLength = $(textArea).text().trim().length;
        	var tipS = '<span class="wordwrap"><var class="textarea_word">' + (maxlength-curLength) + '</var>/' + maxlength + '</span>';
        	var _$tipS = $(tipS);
        	var _$var = _$tipS.find('var');
        	_$tipS.appendTo($(textArea).parent());
        	textArea.on('input propertychange', function () {
              var _value = $(this).val().replace(/\n/gi,"");
              _$var.text(maxlength-_value.length);
          });
        }
    }
    
});