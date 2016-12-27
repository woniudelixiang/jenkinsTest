var Loading = function() {

	return {
		// 打开遮罩层
		blockUI : function(args) {
			var defaults = {
				"el" : $("body"),
				"message" : '<img src="/dao-study/js/jqueryBlockUI/loading3.gif" />',
            	"fadeIn": 300,    // 淡入
            	"fadeOut": 300,   // 淡出
            	
            	// 提示框的样式设置
            	"css" : { backgroundColor: 'none', 
            		      color: 'none',
            		      borderColor:'none',
            		      borderWidth:'0px'      // 无边框
            	         },
            	 
            	 // 遮光罩的样式设置
        	     "overlayCSS" : { backgroundColor: '#000',   // 背景颜色为黑色
        	    	 			  opacity: 0.3,              // 透明度  值越大越不透明，越小越透明
        	    	 			  cursor: 'wait'             // 鼠标的样式 
        	     				},
			};

			args = $.extend(true, defaults, args);
			
			var el = $(args.el); 
			
			return el.block({
				message : args.message,
            	fadeIn : args.fadeIn,
            	fadeOut : args.fadeOut,
            	css :  args.css,
            	overlayCSS : args.overlayCSS,
			});
		},
		
		
		// 关闭遮罩层
		unblockUI: function (args) {
			var defaults = {
					"el" : $("body"),
			}
			args = $.extend(true, defaults, args);
			return $(args.el).unblock({});
		}
		
	};
}();
window.$LD = Loading;