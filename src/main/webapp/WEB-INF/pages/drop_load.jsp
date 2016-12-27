<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tab加载多个数据</title>
    <style>
    *{
        margin: 0;
        padding:0;
        -webkit-tap-highlight-color:rgba(0,0,0,0);
        -webkit-text-size-adjust:none;
    }
    html{
        font-size:10px;
    }
    body{
        background-color: #f5f5f5;
        font-size: 1.2em;
    }
    .tab{
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        height: 44px;
        line-height: 44px;
        border-bottom: 1px solid #ff3c3c;
        background-color: #eee;
    }
    .tab .item{
        display: block;
        -webkit-box-flex: 1;
        -webkit-flex: 1;
        -ms-flex: 1;
        flex: 1;
        width: 100%;
        font-size: 14px;
        text-align: center;
        color: #333;
        text-decoration: none;
    }
    .tab .cur{
        height: 42px;
        border-bottom: 2px solid #ff3c3c;
        color: #ff3c3c;
    }
    .content{
        background-color: #fff;
    }
    .content .item{
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align:center;
        -webkit-box-align:center;
        box-align:center;
        -webkit-align-items:center;
        align-items:center;
        padding:3.125%;
        border-bottom: 1px solid #ddd;
        color: #333;
        text-decoration: none;
    }
    .content .item img{
        display: block;
        width: 40px;
        height: 40px;
        border:1px solid #ddd;
    }
    .content .item h3{
        display: block;
        -webkit-box-flex: 1;
        -webkit-flex: 1;
        -ms-flex: 1;
        flex: 1;
        width: 100%;
        max-height: 40px;
        overflow: hidden;
        line-height: 20px;
        margin: 0 10px;
        font-size: 1.2rem;
    }
    .content .item .date{
        display: block;
        height: 20px;
        line-height: 20px;
        color: #999;
    }
    .opacity{
        -webkit-animation: opacity 0.3s linear;
        animation: opacity 0.3s linear;
    }
    @-webkit-keyframes opacity {
        0% {
            opacity:0;
        }
        100% {
            opacity:1;
        }
    }
    @keyframes opacity {
        0% {
            opacity:0;
        }
        100% {
            opacity:1;
        }
    }
    </style>
    <link rel="stylesheet" href="${ctx }/js/drop_load/dist/dropload.css">
</head>
<body>
<div class="tab">
    <a href="javascript:;" class="item cur">菜单一</a>
    <a href="javascript:;" class="item">菜单二</a>
</div>
<div class="content">
    <div class="lists"></div>
    <div class="lists"></div>
</div>
<!-- jQuery1.7以上 或者 Zepto 二选一，不要同时都引用 -->
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
<%-- <script src="${ctx }/js/drop_load/js/zepto.min.js"></script> --%>
<script src="${ctx }/js/drop_load/dist/dropload.min.js"></script>

<script>
var reqJson={"id":-1};
var reqData={"id":-1};

$(function(){
   //判断页面数据是否加载完毕的数组
	var tabLoadEnd = new Array($('.tab .item').length);
    tabLoadEnd[0]=false;
    $.each(tabLoadEnd, function(i, item){
    	tabLoadEnd[i]=false;
   }); 

   //点击菜单
   $('.tab .item').on('click',function(){
	   $(this).addClass('cur').siblings('.item').removeClass('cur');
       $('.lists').eq($(this).index()).show().siblings('.lists').hide();
       if(!tabLoadEnd[$(this).index()]){
           // 解锁
           dropload.unlock();
           dropload.noData(false);
       }else{
           // 锁定
           dropload.lock('down');
           dropload.noData();
       }
       // 重置
       dropload.resetload();
   });
    
    // dropload
    var dropload = $('.content').dropload({
        scrollArea : window,
        loadDownFn : function(me){
            // 加载菜单一的数据
              if($('.tab .cur').index() == '0'){
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/dropLoad/updateJson',
                    dataType: 'json',
                    data:reqJson,
                    success: function(data){
                         // 数据加载完
                         if(data.length==0){
                        	 tabLoadEnd[$('.tab .cur').index()]=true;
                             // 锁定
                             me.lock();
                             // 无数据
                             me.noData();
                         }else{
                        	 var result = '';
                             for(var i = 0; i < data.length; i++){
                                 result +=  '<a class="item opacity" href="'+data[i].languageId+'">'
                                                 +'<img style="width:40px;height:40px;" alt="" src="${ctx}/images/timeout.jpg" />'
                                                 +'<h3>'+data[i].languageId+'</h3>'
                                                 +'<span class="date">'+data[i].languageName+'</span>'
                                             +'</a>';
                             }
	                         reqJson["id"] = data[data.length-1].languageId;
	                         $('.lists').eq($('.tab .cur').index()).append(result);
                         }
                         // 每次数据加载完，必须重置
                         me.resetload();
                    },
                    error: function(xhr, type){
                        alert('Ajax error!');
                        // 即使加载出错，也得重置
                        me.resetload();
                    }
                });
            // 加载菜单二的数据
            }else if($('.tab .cur').index() == '1'){
                $.ajax({
                	 type: 'POST',
                     url: '${ctx}/dropLoad/updateJson',
                     dataType: 'json',
                     data:reqData,
                    success: function(data){
                          // 数据加载完
                          if(data.length==0){
                        	  tabLoadEnd[$('.tab .cur').index()] = true;
                              // 锁定
                              me.lock();
                              // 无数据
                              me.noData();
                          }else{
                        	  var result = '';
                        	  for(var i = 0; i < data.length; i++){
                                  result +=   '<a class="item opacity" href="'+data[i].languageId+'">'
                                                  +'<img style="width:40px;height:40px;" alt="" src="${ctx}/images/timeout.jpg" />'
                                                  +'<h3>'+data[i].languageId+'</h3>'
                                                  +'<span class="date">'+data[i].languageName+'</span>'
                                              +'</a>';
                                 
                              }
                              reqData["id"] = data[data.length-1].languageId;
                        	  $('.lists').eq($('.tab .cur').index()).append(result);
                          }
                          // 每次数据加载完，必须重置
                          me.resetload();
                     },
                     error: function(xhr, type){
                         alert('Ajax error!');
                         // 即使加载出错，也得重置
                         me.resetload();
                     }
                });
            } 
        }
    });
});
</script>
</body>
</html>