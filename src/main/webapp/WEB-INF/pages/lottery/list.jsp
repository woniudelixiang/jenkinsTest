<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宫格抽奖演示</title>
<style type="text/css">
#lottery {
	width: 574px;
	height: 584px;
	margin: 20px auto 0;
	background: url(${ctx }/images/lottery/bg.jpg) no-repeat;
	padding: 50px 55px;
}

#lottery table td {
	width: 142px;
	height: 142px;
	text-align: center;
	vertical-align: middle;
	font-size: 24px;
	color: #333;
	font-index: -999
}

#lottery table td a {
	width: 284px;
	height: 284px;
	line-height: 150px;
	display: block;
	text-decoration: none;
}

#lottery table td.active {
	background-color: #ea0000;
}
</style>
</head>
<body>
	<input type="button" onclick="test()" value="测试"/>
	<input type="button" onclick="test1()" value="测试 jquery.extend()"/>
	<div id="lottery">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="lottery-unit lottery-unit-0"><img src="${ctx }/images/lottery/1.png"></td>
				<td class="lottery-unit lottery-unit-1"><img src="${ctx }/images/lottery/2.png"></td>
				<td class="lottery-unit lottery-unit-2"><img src="${ctx }/images/lottery/4.png"></td>
				<td class="lottery-unit lottery-unit-3"><img src="${ctx }/images/lottery/3.png"></td>
			</tr>
			<tr>
				<td class="lottery-unit lottery-unit-11"><img src="${ctx }/images/lottery/7.png"></td>
				<td colspan="2" rowspan="2"><a href="#"></a></td>
				<td class="lottery-unit lottery-unit-4"><img src="${ctx }/images/lottery/5.png"></td>
			</tr>
			<tr>
				<td class="lottery-unit lottery-unit-10"><img src="${ctx }/images/lottery/1.png"></td>
				<td class="lottery-unit lottery-unit-5"><img src="${ctx }/images/lottery/6.png"></td>
			</tr>
			<tr>
				<td class="lottery-unit lottery-unit-9"><img src="${ctx }/images/lottery/3.png"></td>
				<td class="lottery-unit lottery-unit-8"><img src="${ctx }/images/lottery/6.png"></td>
				<td class="lottery-unit lottery-unit-7"><img src="${ctx }/images/lottery/8.png"></td>
				<td class="lottery-unit lottery-unit-6"><img src="${ctx }/images/lottery/7.png"></td>
			</tr>
		</table>
	</div>
	
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${ctx }/js/lottery.js"></script>
	<script type="text/javascript" src="${ctx }/js/ieCommon.js"></script>
	
	<script type="text/javascript">
		function test(){
			 $IEC.ajaxCall({
					uri :"${ctx}/lottery/getPrizeInfo",
					success : function(data) {
						//alert("success");
						data = JSON.parse(data);
						data = data.datas;
						 // 概率数组
						 var probabilityArray = [];
						  $.each(data, function(i, item){   
							  probabilityArray.push(item.probability)
						  }); 
						 
						 // 根据概率获取奖项index
						 var index =  getRand(probabilityArray);
						 // 获得的奖励
						 var gainPrize = data[index];
						 
						 // 1."归零" 
						 $LC.clear(); 
						 // 2.初始化
						 $LC.init({
							 "prize" : gainPrize.id,
						 });
						 // 3.转动
						 $LC.roll();
					}
				});
		
		// 根据概率产生随机数
		function getRand(probabilityArray){
			// 随机数的下标
			var index = -1;  
			// 概率数组的总概率 
			var sum = 0;
			
			// 求概率总和
			$.each(probabilityArray, function(i, item){    
				sum += item
			}); 
			
			// 根据概率产生随机数
			$.each(probabilityArray, function(i, item){
				// 随机生成1到sum的整数
				var randomNum = parseInt(Math.random() * (sum - 1 + 1) + 1); 
				// console.log("randomNum: " + randomNum + " item:" + item + "  sum: " + sum);
				if(randomNum <= item){ //中奖
					index = i;
					return false;
				}else{
					sum -= item;
				}
			}); 
			return index;
		}
	}	
	</script>
</body>

</html>