function Dd(i) {return document.getElementById(i);}
function Ds(i) {Dd(i).style.display = '';}
function Dh(i) {Dd(i).style.display = 'none';}
function Go(url) {window.location = url;}

// 当url不存在（''或者undefined）时，执行window.history.back();否则跳转到指定的url
function Dback(url){
	if(url){
		// 当url存在时，跳转到指定的url
		Go(url);
	}else{
		// 当url不存在（''或者undefined）时执行
		window.history.back();
	}
}

// 前后去空格
function DTrim(s) {
	if(s){
		s = s.trim();
	}
	return s;
}

function editOperation(formId, editUrl, hideJson) {
	var formObj = $("#"+formId);
	formObj.attr("action", editUrl);
	// 一般是被修改记录的id
	$.each(hideJson,function(key,value){
		$("#"+key).val(value);
	});
	formObj.submit();
}
