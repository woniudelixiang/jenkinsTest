1、jquery.autocomplete API
　　语法： autocomplete(url/data, [options] ) 
　　参数： url / data：url或者数组 [options]：可选项

2、[options]：可选项，选项解释如下：
　　minChars: 0, 　　　　　　　　     //至少输入的字符数，default：1；如果设为0，在输入框内双击或者删除内容时显示列表。 
　　width: 220,　　　　　　　　　  //下拉框的宽度，default：input元素的宽度
　　max: 10, 　　　　　　　　　　    	//下拉项目的个数，default：10
　　scrollHeight: 300,　　　　　 	// 下拉框的高度， Default: 180
　　scroll: true,　　　　　　　　 //当结果集大于默认高度时，是否使用滚动条，Default: true
　　multiple: false, 　　　　　　	//是否允许输入多个值. Default: false
　　autoFill: false,　　　　　　   // 是否自动填充. Default: false
　　multipleSeparator: " ",　　    //输入多个字符时,用来分开各个的字符. Default: ","
　　matchCase:false,　　　　     	//是否开启大小写敏感
　　selectFirst:true,    　　　　　  // 如果设置成true,下拉列表的第一个值将被自动选择, Default: true
　　matchSubset:true,    　　　   	//是否启用缓存
　　cacheLength: 10,    　　    　　 	//缓存的长度.即缓存多少条记录.设成1为不缓存.Default: 10
　　delay: 20,    　　　　　　　　 	//击键后的延迟时间(单位毫秒).Default: 远程为400 本地10
　　mustMatch:false,    　　    　  	//如果设置为true,只会允许匹配的结果出现在输入框,当用户输入的是非法字符时,将被清除， Default: false
　　matchContains:true,    　　 		//决定比较时是否要在字符串内部查看匹配.Default: false

<!-- use -->
$("#auto").autocomplete(
    {
        source: url,
        minLength: 2
    }
);

<!-- 后台获取 -->
String q = request.getParameter("term");