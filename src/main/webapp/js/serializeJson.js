/*
 * 功能；扩展jQuery表单序列化函数, 将表单序列化为JSON对象 
 * 表单元素命名规则： 
 *	  格式1： name="country.id" ======>>>序列化成   {"country":{"id":""}}
 *	 格式2： name="age"  ======>>> 序列化成   {"age":""}
 *	 格式3： name="name" name="name"  ======>>> 序列化成 {"name":["",""]}
 * 调用方法：  var result = $("#表单的id").serializeJson();
*/
(function($){
	/**
	 * 将表单元素序列化成JSON对象
	 */
    $.fn.serializeJson = function(){
    	// 最终序列化的结果
        var serializeObj = {};
        // 序列化成数组
        var array = this.serializeArray();
        $LU.debug("序列化成数组 : " + JSON.stringify(array));
        $(array).each(function(){
        	// 如果值为 undefined 或者 ""忽略
        	if($CU.isEmpty(this.value)){
        	     return true;  // return true 结束本轮循环进入下轮循环 ( 相当于continue),  return false //退出循环 ( 相当于break)
        	}
        	// 建立JSON对象
        	buildJson(this.name,this.value,serializeObj);
        });
        // 返回序列化的结果
        return serializeObj;  
    };
    
    /**
     *  建立JSON对象
     *  @param name       键
     *  @param value      值
     *  @param serializeObj  键值对需要放入的对象
     */
    var buildJson = function (name, value, serializeObj) {
    	$LU.debug("buildJson方法传入的参数     name： "+ name + " ，  value： " + value +" ， serializeObj： " + JSON.stringify(serializeObj));
    	// 键值对需要放入的对象
    	var currentObj = serializeObj;
    	// 获取键是否含有多级属性
    	var index = name.indexOf(".");
    	
    	//多级属性 即封装成对象中对象的情况
    	if(index != -1){
    		$LU.debug("----------->>> 多级属性 即封装成对象中对象的情况 ");
    		// 获取多级属性"第一部分"
    		var firstPart = name.substr(0, index);
    		// 获取多级属性"其余部分"
    		var lastPart = name.substr(index+1, name.length);
    		$LU.debug("第一部分（firstPart）: " + firstPart +" ， 其余部分（lastPart）: " + lastPart);
    		
    		// 属性"无限极"情况 进行递归处理
    		if(lastPart.indexOf(".") != -1){
    			$LU.debug("==========================>>> 递归...");
    			// 键值对需要放入的对象
    			currentObj = $CU.isUndefined(serializeObj[firstPart]) ? {} : serializeObj[firstPart];
    			// 建立JSON对象
    			buildJson(lastPart, value, currentObj);
    		}else{
    			$LU.debug("最深层次了，开始向当前对象添加键值对...");
    			// 当"firstPart"在当前对象中不存在时，添加一个空对象
    			serializeObj[firstPart] = $CU.isUndefined(serializeObj[firstPart]) ? {} : serializeObj[firstPart];
    			// 向当前对象中添加键值对
    			serializeObj[firstPart][lastPart] = value;
    		}
    	}else {  // 单级属性组装 
    		$LU.debug("========= 单级属性组装情况 ");
    		// 如果键存在的情况，封装成数组
    		if(serializeObj[name]){
    			$LU.debug("如果键存在的情况，封装成数组");
    	        // 如果当前键已经是数组，      $.isArray(object)用来测试object对象是否是一个JavaScript数组
    			if($.isArray(serializeObj[name])){
    				$LU.debug("已经是数组，向数组中添加数据...");
    				// 向数组中添加数据
    	            serializeObj[name].push(value);
    	        }else{ 
    	        	$LU.debug("当前键不是数组，将当前键的值和目前的值组装成数组...");
    	        	//当前键不是数组，将当前键的值和目前的值组装成数组
    	            serializeObj[name] = [serializeObj[name],value];  
    	        }  
    	    }else{
    	    	// 如果键不存在的情况，封装成对象
    	    	$LU.debug("如果键不存在的情况，封装成对象");
    	        serializeObj[name] = value;   
    	    } 
    	}
    };
})(jQuery); 