var SweetCommon = function() {
	return {
		swal : function(args) {
			
			var defaults = {
					title : '',
					text : '',
					html : '',
					timer : '',
					type : '', 
					imageUrl : '',
					imageWidth: '',
				    imageHeight: '',
					showCloseButton : false,
				    showCancelButton : false,
				    showConfirmButton: false,
				    confirmButtonText : '',
				    cancelButtonText : '',
				    confirmButtonColor : '#3085d6',
				    cancelButtonColor : '#d33',
				    confirmButtonClass : '',         
				    cancelButtonClass : '', 
				    buttonsStyling : true,
				    padding: 15,			   
				    background: '',
				    animation : 'none',
				    allowOutsideClick : false,
				    showLoaderOnConfirm : true,    //点击确认后显示加载效果
				    width : 500,
				    preConfirm : '',
			};

			args = $.extend(true, defaults, args);

			//alert(JSON.stringify(args));
			
			return swal({
				title : args.title,                            // 提示框标题
				text : args.text,                              // 提示文本内容
				html : args.html,                              // 提示HTML内容
				timer : args.timer,                            // 自动关闭提示框时间（毫秒）。
				type : args.type,                              // 提示类型，有：info（提示） ，question（询问） ，warning（警告）， success（成功），error（错误）。
				imageUrl : args.imageUrl,                      // 定义弹出框中的图片地址
				imageWidth: args.imageWidth,                   //图片的宽度
			    imageHeight: args.imageHeight,                 //图片的高度
				showCloseButton : args.showCloseButton,        // 是否显示“关闭”按钮
				
				showConfirmButton : args.showConfirmButton,    // 是否显示“确认”按钮
			    showCancelButton : args.showCancelButton,      // 是否显示“取消”按钮
			    confirmButtonText : args.confirmButtonText,    // 确认按钮上的文本
			    cancelButtonText : args.cancelButtonText,      // 取消按钮上的文本
			    confirmButtonColor : args.confirmButtonColor,  // 确认按钮的背景颜色
			    cancelButtonColor : args.cancelButtonColor,    // 取消按钮的背景颜色
			    confirmButtonClass: args.confirmButtonClass,   // 确认按钮的样式
			    cancelButtonClass: args.cancelButtonClass,     // 取消按钮的样式
			    buttonsStyling : args.buttonsStyling,          // 是否为按钮添加默认的swal2样式
			    padding: args.padding,    			           // 提示框的内边距
			    background: args.background,                   // 提示框的背景
			    animation : args.animation,
			    allowOutsideClick : args.allowOutsideClick,    // 如果设置为“true”，用户可以通过点击警告框以外的区域关闭警告框。
			    showLoaderOnConfirm: args.showLoaderOnConfirm,    //点击确认后是否显示加载效果
			    width: args.width,                                // 提示框的宽度
			    preConfirm : args.preConfirm,   
			}).then(function(isConfirm) { 
			      if (isConfirm) {
				        // swal('Deleted!', 'Your file has been deleted!', 'success');
			    	  alert('确认');
				      }else if(!isConfirm){
				        // swal('Cancelled', 'Your imaginary file is safe :)', 'error');
				    	  alert("取消");
				      }
			});
		}
	};
}();

window.$SC = SweetCommon;