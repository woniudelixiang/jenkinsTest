var ApplicationUtil = function() {
	
	return {
		
		/**
		 * 获取项目的绝对路径
		 * @returns
		 */
		getBasePath : function (){
			// 获取当前网址    http://localhost:8080/dao-study/jqueryValidate/demo1
			var curPath = window.document.location.href; 
			
			//获取主机地址之后的目录，如： /dao-study/jqueryValidate/demo1
			var pathName=window.document.location.pathname; 
			
			var index=curPath.indexOf(pathName);
			//获取主机地址，如： http://localhost:8080
			var localhostPath=curPath.substring(0,index);
			
			//获取带"/"的项目名，如：/dao-study
			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
			
			//得到了 http://localhost:8083/myproj
			var basePath = localhostPath+projectName;
			
			return basePath;
		},
		
	};
}();
window.$AppU = ApplicationUtil;