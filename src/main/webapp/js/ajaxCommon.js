var ajaxCommon = function() {
	function parseJson(text) {
		try {
			return JSON.parse(text);  // ie 8
		} catch (e) {
			return eval('(' + text + ')'); // ie7
		}
	}

	function json2str(jsonObj) { 
		try {
			return JSON.stringify(jsonObj);  // ie 8 
		} catch (e) {
			return jsonObj.toJSONString(); // ie7
		}
	}
	
	return {
		
		/**
		 * AJAX基础访问方法
		 * @param args
		 * @returns
		 */
		ajaxCall : function(args) {
			var defaults = {
				"uri" : "",   // 发送请求的地址
				"type" : "GET",   // 请求方式（post或get）
				"data" : {},  // 要求为Object或String类型的参数，发送到服务器的数据。
				"dataType" : "json",  // 要求为String类型的参数，预期服务器返回的数据类型
				
				"headers" : {          // 请求头部
						"Content-Type" : "application/json",
						"Accept" : "application/json"
				},
				"timeout" : 30000,  // 设置请求超时时间（毫秒）
				"async" : true,      //  是否异步请求
				"cache" : false,  // 是否从浏览器缓存中加载请求信息
				"success" : function(data, textStatus){}, // 请求成功后调用的回调函数
				"erorr" : function(XMLHttpRequest, textStatus, errorThrown) {alert("系统错误,请重试");}, // 请求失败时被调用的函数
			};

			args = $.extend(true, defaults, args);

			var dataStr = "";

			return $.ajax({
				url : args.uri + '?' + Math.floor(Math.random() * 100),
				type : args.type,
				dataType : args.dataType,
				headers : args.headers,
				timeout : args.timeout,
				data : json2str(args.data),
				success : args.success,
				error : args.error,
				async : args.async
			});
		},
		
	};
}();
window.$AC = ajaxCommon;