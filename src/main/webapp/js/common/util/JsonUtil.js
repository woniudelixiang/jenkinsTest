var JsonUtil = function() {
	
	return {
		toJson : function (text) {
			try {
				return JSON.parse(text);  // ie 8
			} catch (e) {
				return eval('(' + text + ')'); // ie7
			}
		},
		
		toString : function (jsonObj) { 
			try {
				return JSON.stringify(jsonObj);  // ie 8 
			} catch (e) {
				return jsonObj.toJSONString(); // ie7
			}
		},
		
	};
}();
window.$JU = JsonUtil;