var CheckUtil = function() {
	
	return {
		
		/**
		 * 判断一个对象是否定义
		 * @param obj
		 * @returns
		 */
		isNotUndefined : function(obj) {
			if (obj != undefined) {
				return true;
			}
			return false;
		},

		/**
		 * 判断一个对象是否未定义
		 * @param obj
		 * @returns
		 */
		isUndefined : function(obj) {
			return !this.isNotUndefined(obj);
		},
		
		/**
		 * 判断当前对象是否不为空
		 * @param obj
		 * @returns
		 */
		isNotEmpty : function(obj) {
			if (obj != undefined && obj != "") {
				return true;
			}
			return false;
		},
		
		/**
		 * 判断当前对象是否为空
		 * @param obj
		 * @returns
		 */
		isEmpty : function(obj) {
			return !this.isNotEmpty(obj);
		},
		
		
		

	};
	
	
}();
window.$CU = CheckUtil;