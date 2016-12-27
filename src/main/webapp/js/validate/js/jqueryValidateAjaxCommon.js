var jqueryValidateAjaxCommon = function() {
	
	var defaults = {
			formId :'validateForm',
			meta : 'validate',
			submitFormType : 0,  //submitFormType  0 表示普通提交    1表示ajax提交
			ajaxSubmitForm : function (form){},
		};
	
	return {
		// zTree公共参数设置
		bindValidate : function(args) {
			args = $.extend(true, defaults, args);
			jQuery.metadata.setType("attr", args.meta); 
			var validate = $("#"+args.formId).validate({
				debug:true,  //调试模式，即使验证成功也不会跳转到目标页面
				onsubmit : true, // 是否在提交时验证        default: true 
				
				// 当键盘按键按下被松开时触发（用该事件来实现表单元素的实时验证）
				onkeyup:function(element){
					$LU.debug($(element).attr("id") + '------触发onkeyup方法实现实时验证-------');
					//$(element).valid();  // 触发校验功能
					this.element( element );   // 触发校验功能
			    	//帮助提示信息移除
					$JVAC.removeTip(element);
				},
				
				//指定验证通过/不通过时，提示信息显示的位置
				errorPlacement : function(error, element) {
					$LU.debug($(element).attr("id") + '------触发errorPlacement方法指定验证通过/不通过时，提示信息显示的位置-------');
					$JVAC.placement(error,element);
				}, 
				
				//验证通过时的调用-添加样式
				success : function(label) {
					$LU.debug('------验证通过时的调用-添加样式-------');
					label.html("&nbsp;").addClass("right").text('通过');
				},
				
			    /*重写校验元素获得焦点后的执行函数--增加[1.光标移入元素时的帮助提示,2.校验元素的高亮显示]两个功能点*/
			    onfocusin: function( element ) {
			    	$LU.debug('------校验元素获得焦点后的执行（onfocusin）-------');
			    	/*帮助提示信息移除*/
			    	$JVAC.removeTip(element);
			    	
			    	this.lastActive = element;
			        // 隐藏改元素的其他验证信息
			        this.addWrapper(this.errorsFor(element)).hide();   
			        /*1.帮助提示功能*/
			        var tip = $(element).attr('tip');
			        if(tip && $(element).parent().children(".tip").length === 0){
			        	var tipD = $("<label class='tip'>" + tip + "</label>");
			        	// 指定提示元素显示的位置
			        	$JVAC.placement(tipD,element);
			        }
			        /*2.校验元素的高亮显示*/
			        $(element).addClass('highlight');
			    },
			    
			    /*重写校验元素焦点离开时的执行函数--移除[1.添加的帮助提示,2.校验元素的高亮显示]*/
			    onfocusout: function( element ) {
			    	$LU.debug('------校验元素失去焦点后的执行（onfocusout）-------');
			    	/*1.帮助提示信息移除*/
			    	$JVAC.removeTip(element);
			        /*2.校验元素高亮样式移除*/
			        $(element).removeClass('highlight');
			        /*3.触发校验功能*/
			        this.element( element );
			    },
			    
			    //触发提交表单，且表单元素全部验证通过时的调用
				submitHandler: function(form, validator) {
					$LU.debug('触发提交表单，且表单元素全部验证通过时的调用（submitHandler）...............');
					//获取提交类型类型   0普通提交       1ajax提交
					var submitFormType = parseInt(args.submitFormType);
					if(submitFormType==0){
						$LU.debug('submitFormType: '+ submitFormType + '------>>>>  普通表单提交');
						form.submit();
					}else if(submitFormType==1){
						$LU.debug('submitFormType: '+ submitFormType + '------>>>>  ajax提交');
						args.ajaxSubmitForm(form);
					}else{
						$LU.debug('submitFormType: '+ submitFormType + '------>>>>  普通表单提交');
						form.submit();
					}
				},
			    
			});
			return validate;
		},
		
		// 统一管理提示信息的位置
		placement : function (newElement,element){
			console.log('指定新增元素显示的位置 ....');
			element = $(element);
			
			newElement.insertAfter(element.parent());
//			if (element.is(":radio"))
//				 newElement.appendTo(element.parent());//放在父元素的后面
//				else if (element.is(":checkbox"))
//					newElement.appendTo(element.parent());
//				else if (element.is("input[name=captcha]"))
//					newElement.appendTo(element.parent());
//				else
//					newElement.insertAfter(element);//放在当前元素的后面
		},
		
		// 移除tip提示
		removeTip: function (element){
			 $(element).parent().parent().children(".tip").remove();
		},

	};
}();
window.$JVAC = jqueryValidateAjaxCommon;