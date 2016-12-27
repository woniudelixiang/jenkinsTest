<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>阻止Backspace事件演示</title>
<link rel="stylesheet" type="text/css" href="${ctx }/js/passwordStrength/style.css" /> 
</head>
<body>
           <input type="text"/><br><br>
 readonly: <input type="text" readonly="readonly"/><br><br>
 disabled: <input type="text" disabled="disabled"/><br><br>
 textarea: <textarea rows="5" cols="15"></textarea><br><br>
 textarea_disabled: <textarea rows="5" cols="15" disabled="disabled"></textarea><br><br>
 textarea_readonly: <textarea rows="5" cols="15" readonly="readonly"></textarea><br><br>
 password: <input type="password"/><br><br>
 file:     <input type="file"/><br><br>


<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
/* Jquery根据标签名称获得该元素的方法
	$(this).get(0).body
	$( this )[0].tagName
	$( this ).prop("tagName")
	$( this ).prop("nodeName") 
*/

 $(function(){
	$(this).get(0).body.onkeydown =function(){ 
		 if(event.keyCode==8){//判断按键为BackSpace键  
			 //获取按键按下时光标所指向的element  
             var elem = event.srcElement || event.currentTarget;   
             //获取按键按下时光标所指向的element的名称
             var name = elem.nodeName;  
             
             //  不是表单元素都阻止
             if(name!='INPUT' && name!='TEXTAREA'){  
            	 debugger;
                 return _stopIt(event); 
             }
             
            // 获取按键按下时光标所指向的element的类型
             var type_e = elem.type.toUpperCase();  
             
            // 表单元素中的 text, passwoerd不阻止
             if(name=='INPUT' && (type_e!='TEXT' && type_e!='PASSWORD')){ 
            		 //阻止
                     return _stopIt(event); 
             }
             
            // 表单元素 readOnly==true 和  disabled ==true的元素阻止
             if((name=='INPUT' || name=='TEXTAREA') && (elem.readOnly==true || elem.disabled ==true)){ 
            	     //阻止
                     return _stopIt(event);  
             }
            
		 }
	}
});


function _stopIt(e){
	//event.preventDefault()方法是用于取消事件的默认行为，但此方法并不被ie支持，在ie下需要用window.event.returnValue = false; 来实现。
    if(e.returnValue){  
        e.returnValue = false ;  
    }
    
    if(e.preventDefault ){  
        e.preventDefault();  
    }
    
    return false;  
} 
</script>


<!-- <script type="text/javascript">
window.onload=function(){  
    document.getElementsByTagName("body")[0].onkeydown =function(){  
          debugger;
        if(event.keyCode==8){//判断按键为BackSpace键  
                //获取按键按下时光标所指向的element  
                var elem = event.srcElement || event.currentTarget;   
                //获取按键按下时光标所指向的element的名称
                var name = elem.nodeName;  
                  
                if(name!='INPUT' && name!='TEXTAREA'){  
                    return _stopIt(event); 
                }
                
              //获取按键按下时光标所指向的element的类型
                var type_e = elem.type.toUpperCase();  
                
                if(name=='INPUT' && (type_e!='TEXT' && type_e!='TEXTAREA' && type_e!='PASSWORD' && type_e!='FILE')){  
                        return _stopIt(event); 
                }
                
                if(name=='INPUT' && (elem.readOnly==true || elem.disabled ==true)){  
                        return _stopIt(event);  
                }
                
            }  
        };
    };
    
function _stopIt(e){  
        if(e.returnValue){  
            e.returnValue = false ;  
        }
        
        if(e.preventDefault ){  
            e.preventDefault();  
        }                 
        return false;  
}

</script> -->



</body>
</html>