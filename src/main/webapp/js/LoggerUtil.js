var LoggerUtil = function() {
	var	isDebug = true;
	
	return {
		/**
		 * 在控制台输出日志
		 * @params message 要输出的日志信息              
		 */
		debug : function(message) {
			try {
				//debugger;
				if (!isDebug) {
					return;
				}
				if (!window.console) {
					window.console = {};
					window.console.log = function() {
						return;
					};
				}
				window.console.log(message + ' ');
			} catch (e) {

			}
		},
		
		/**
		 * debug是否可用
		 * @param bDebug 为true或者不传时可用，false时不可用
		 */
		openDebug : function(isOpen) {
			isDebug = isOpen == undefined ? true : isOpen && true;
		},

	};
}();
window.$LU = LoggerUtil;