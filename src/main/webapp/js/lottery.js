var LotteryCommon = function() {
	var defaults = {
			selectorId : '#lottery',
			selectorClass : '.lottery-unit',
			obj : '',
			click : true,   // 转盘是否可以点击
			index : -1, //当前转动到哪个位置，起点位置
			count : 0, //总共有多少个位置
			timer : 0, //setTimeout的ID，用clearTimeout清除
			speed : 150, //初始转动速度
	        endDownStep:     110,   // 结束减速滚动步长
			downMax : 2000,  // 减速上限  
			upMin: 50,   // 加速下限  
			times : 0, //转动次数
			upStep : 10,   // 加速滚动步长  
			baseCycle : 30, // 转动基本次数：即至少需要转动多少次再进入抽奖环节
			downStep:     5,   // 减速滚动步长
			cycle : 10, // 提速次数：即计算出奖项后，至少需要转动多少次才决定停止
			slowIndex : 5, //减速位置
			prize : -1, //中奖位置
		};
	
	var resetDefaults={};
	
	return {
		init : function(args) {
			// resetDefaults = $.extend(true, {}, defaults, resetDefaults);
			
			defaults = $.extend(true, defaults, args);
			$lottery = $(defaults.selectorId);
			$units = $lottery.find(defaults.selectorClass);
			if ($units.length > 0) {
				defaults.count = $units.length;
				
				defaults.obj = $lottery;
				// 计算减速位置
				var slowIndex  = defaults.prize - defaults.slowIndex;
				
				if(slowIndex < 0){
				   slowIndex  =  defaults.count - Math.abs(slowIndex);
				}
				defaults.slowIndex = slowIndex;
				$lottery.find(defaults.selectorClass + "-" + defaults.index).addClass("active");
				
				console.log("---------->>>  "+JSON.stringify(resetDefaults));
			}
		},
		
		singleStepRoll : function() {
			var index = defaults.index;
			var count = defaults.count;
			var $lottery = defaults.obj;
			$lottery.find(defaults.selectorClass + "-" + index).removeClass("active");
			index = (defaults.index+1) % count; // 计算下一个位置
			$lottery.find(defaults.selectorClass + "-" + index).addClass("active");
			defaults.index = index;
			return false;
		},
		
		// "归零"
		clear : function(){
			resetDefaults = $.extend(true, {}, defaults, resetDefaults);
			console.log("******************  " + JSON.stringify(resetDefaults));
			// 转盘复位
			var $lottery = defaults.obj;
			if($lottery){
				$lottery.find(defaults.selectorClass + "-" + defaults.index).removeClass("active");
			}
			
			defaults = $.extend(true, {}, resetDefaults);
			console.log("*********defaults*********  " + JSON.stringify(defaults));
			
			//defaults = resetDefaults;
			
			/*defaults.prize = -1;
			defaults.times = 0;
			defaults.slowIndex = -1;
			defaults.timer = 0; //setTimeout的ID，用clearTimeout清除
			defaults.speed = 150; //初始转动速度
			defaults. endDownStep =     110;   // 结束减速滚动步长
			defaults.downMax = 2000;  // 减速上限  
			defaults.upMin= 50;   // 加速下限  
			defaults.times = 0; //转动次数
			defaults.upStep = 10;   // 加速滚动步长  
			defaults.baseCycle = 20; // 转动基本次数：即至少需要转动多少次再进入抽奖环节
			defaults.downStep=     5;   // 减速滚动步长
			defaults.cycle = 10; // 提速次数：即计算出奖项后，至少需要转动多少次才决定停止
			defaults.slowIndex = 3; //减速位置
			defaults.prize = -1; //中奖位置
			defaults.click = true;*/
		},
		
		// 是否开启到终点的减速
		isSlow : function(index,slowIndex,prize){
			var result = false;
			if (slowIndex > prize) {
				if (index >= slowIndex || index <= prize) {
					result = true;
				}
			} else {
				if (index >= slowIndex && index <= prize) {
					result = true;
				}
			}
			console.log("当前位置：" + index + "  减速开始位置：" + slowIndex + "  减速结束位置：" + prize + "  是否减速：" + result);
			return result;
		},
		
		
		 roll : function(){
			 $LC.singleStepRoll();  //转动一次
			 defaults.times += 1;   // 转动次数+1
			 console.log("调用前：" + defaults.times + '********'+ (defaults.baseCycle + defaults.cycle) + '*******' + defaults.index + '*******' + defaults.prize + "------- " + defaults.speed);
			if (defaults.times > defaults.baseCycle + defaults.cycle && defaults.prize == defaults.index) {
				console.log("**** 停止业务处理 ****");
				clearTimeout(defaults.timer);  // 清除（取消）计时器
				console.log('你中奖了' + (".lottery-unit-" + defaults.index));   // 给用户提示
				//$LC.clear(); // 计数属性清零
			} else {
				if (defaults.times <= defaults.baseCycle) {
					defaults.speed -= defaults.upStep;  // 加速
				} else {
					if (defaults.times > defaults.baseCycle + defaults.cycle && $LC.isSlow(defaults.index, defaults.slowIndex, defaults.prize)) {
						defaults.speed += defaults.endDownStep;   // 快到终点的减速
					} else {
						defaults.speed += defaults.downStep;      // 转过基圈后的减速
					}
				}
				
				defaults.speed < defaults.upMin ? defaults.speed = defaults.upMin : true;  // 加速下限的设置
				defaults.speed > defaults.downMax ? defaults.speed = defaults.downMax : true;  // 减速上限的设置
				
				defaults.timer = setTimeout($LC.roll, defaults.speed);
			}
			return false;
		},
		
		
		
		
	};
}();

window.$LC = LotteryCommon;