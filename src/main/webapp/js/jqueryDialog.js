var DialogCommon = function() {
	return {
		jqueryDialog : function(args) {
			var defaults = {
					"dialogId":"dialogId",  //弹窗的id
					"autoOpen":false,  //初始化时窗口处于关闭状态
					"bgiframe":false,  //解决 IE6 的 select元素穿透问题，默认为true
					"closeOnEscape":true,  //true表示按Esc键退出默认窗口默认的状态
					"draggable": false, // 是否允许拖动，默认为 true
					"resizable":false,   // 是否可以调整对话框的大小，默认为 true
					"width":390, //窗口宽度
					"height":270,
					"title":"分级别", //设置窗口的标题
					"modal":true, //遮罩效果默认是false不遮住
					"position":"center", //对话框弹出的位置，top,left,right,center,默认是center
					 "open":function(){},  // 对话框打开后，触发此事件
					 "beforeClose":function(){}, // 对话框关闭之前，触发此事件。如果返回false，则对话框仍然显示。   
					 "close":function(){},  // 弹窗关闭之后触发此事件
					"buttons":{},
			};

			args = $.extend(true, defaults, args);

			//alert(JSON.stringify(args));
			
			return $("#"+args.dialogId).dialog({
				"autoOpen":args.autoOpen,
				"bgiframe":args.bgiframe,
				"closeOnEscape":args.closeOnEscape,
				"draggable": args.draggable, 
				"resizable":args.resizable, 
				"width":args.width, 
				"height":args.height,
				"title":args.title,
				"modal":args.modal, 
				"position":args.position, 
				"buttons":args.buttons,
				"open":args.open,
				"close":args.close,
				"beforeClose":args.beforeClose,
			});
		}
	};
}();

window.$DC = DialogCommon;