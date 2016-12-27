/*
 * jQuery validation 验证类型扩展
 * 
 * addMethod(参数1,参数2)
 * 第1个参数是:方法名称
 * 第2个参数是:验证方法，验证方法的参数有三个,分别是：（被验证元素的值，被验证元素，参数）
 */

//手机号码验证
$.validator.addMethod("cellphone", function(value, element) {
    var cellphone = /^((\+86)|(86))?1[3|4|5|8][0-9]{9}$/;
    return this.optional(element) || cellphone.test(value);
}, "请输入正确的手机号码!");  

//用户名字符验证    
jQuery.validator.addMethod("userName", function(value, element) {    
	var username=/^[\u0391-\uFFE5\w]+$/;
  return this.optional(element) || username.test(value);    
}, "用户名只能包括中文字、英文字母、数字和下划线!");  

//只能是中文或空格验证    
jQuery.validator.addMethod("Chinese", function(value, element) {    
	 //var reg = /^[\u4e00-\u9fa5| ]*$/;  
	var reg = /^[\u4E00-\u9FA5\s]*$/;
	return this.optional(element) || reg.test(value);    
}, "只能输入中文!");   

//只能是英文和空格验证    
jQuery.validator.addMethod("English", function(value, element) {    
	var reg = /^[a-zA-Z\s]*$/;  
	return this.optional(element) || reg.test(value);    
}, "只能输入英文!");   

//必填项验证【-99算没有填值】
jQuery.validator.addMethod("extRequired", function(value, element) {  
	//debugger;
	if(value==null || value=="" || value==undefined || value=='-99' ){
		return false;    
	}
	return this.optional(element) || true;    
}, "必填项"); 

//必填项验证【"  "算没有填值】
jQuery.validator.addMethod("notBlank", function(value, element) {
	if(value==null || value=="" || value==undefined || value.trim()=='' ){
		return false;
	}
	return this.optional(element) || true;    
}, "必填项");

//最大字数
jQuery.validator.addMethod("maxSize", function(value, element, param) {    
	 //console.log("111");
	if(value.length>param){
		return this.optional(element) || false;
	}
	return this.optional(element) || true;    
}, "字数不能超过{0}个字"); 


//相等【在不为空的情况下验证注意；equalTo在为空的情况下也好验证】
jQuery.validator.addMethod("equalToOnNotEmpty", function(value, element, param) {    
	var target = $(param).unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
		$(element).valid();
	});
	return this.optional(element) || value == target.val();
}, "请再次输入相同的值!");   


jQuery.validator.addMethod("regex" , function(value, element, params) {    
    var exp = new RegExp(params);  //实例化正则对象，参数为传入的正则表达式
    return this.optional(element) || exp.test(value);     //测试是否匹配【在填写值的情况下验证】
}, "格式错误"); 

// 电话号码验证    
jQuery.validator.addMethod("telephone", function(value, element) {    
  var tel = /^(\d{3,4}-?)?\d{7,9}$/g;    
  return this.optional(element) || (tel.test(value));    
}, "请正确填写您的电话号码!");

 // 邮政编码验证    
jQuery.validator.addMethod("isZipCode", function(value, element) {    
  var zip = /^[0-9]{6}$/;    
  return this.optional(element) || (zip.test(value));    
}, "请正确填写您的邮政编码!");     

// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
  var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;   
  return this.optional(element) || (idCard.test(value));    
}, "请输入正确的身份证号码!"); 

//邮箱验证
jQuery.validator.addMethod("isEmail", function(value, element) { 
  var idCard = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;   
  return this.optional(element) || (idCard.test(value));    
}, "邮箱格式不正确!"); 

// IP地址验证   
jQuery.validator.addMethod("ip", function(value, element) {    
  return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
}, "请填写正确的IP地址！");

