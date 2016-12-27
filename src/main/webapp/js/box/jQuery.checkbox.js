/**  js对checkbox的操作    
 *   使用时父复选框必须使用该格式；
 *   <input type="checkbox" name="checkAll" id="checkAll"
 *			                 onclick="CheckboxUtils.checkAll(this,'子复选框的名字')" />全选/全不选<br/>
 *   <input type="checkbox" name="uncheckAll" id="uncheckAll"
			onclick="CheckboxUtils.uncheckAll('子复选框的名字')" />反选<br />
 */

jQueryCheckboxUtils = {
	// ============================默认的属性==============================================

	isDebug : true,

	// ============================debug是否启用===========================================

	/**
	 * debug是否可用
	 * 
	 * @param bDebug
	 *            为true或者不传时可用，false时不可用
	 */
	offDebug : function(bDebug) {
		this.isDebug = bDebug == undefined ? true : bDebug && true;
	},

	// ============================方法==================================================

	/**
	 * 操作父复选框来改变子复选框的选中状态
	 * 
	 * @param obj
	 * @param checkboxName
	 */
	checkAll : function(obj, checkboxName, uncheckboxName) {
		try {
			//保证参数都是必须提供的
			if(obj==undefined || checkboxName==undefined ||uncheckboxName==undefined){
				this.debug('调用checkAll()方法时提供的参数不够');
				return false;
			}
			// 让子复选框跟着父复选框状态改变而改变
			$("input:[name=" + checkboxName + "][type='checkbox']").each(
					function() {
						$(this).attr('checked', $(obj).is(':checked'));
					});
			// 让反选框不选中
			$("input:[name=" + uncheckboxName + "][type='checkbox']").each(
					function() {
						$(this).attr('checked', false);
					});
		} catch (e) {
			this.debug('checkAll()方法出现异常.' + e.message);
		}
	},
	
	/**
	 * 全不选
	 * 
	 * @param obj
	 * @param checkboxName
	 */
	unCheckAll : function(checkboxName) {
		try {
			//保证参数都是必须提供的
			if(checkboxName==undefined){
				this.debug('调用unCheckAll()方法时提供的参数不够');
				return false;
			}
			// 全不选
			$("input:[name=" + checkboxName + "][type='checkbox']").each(
					function() {
						$(this).attr('checked', false);
					});
		} catch (e) {
			this.debug('unCheckAll()方法出现异常.' + e.message);
		}
	},

	/**
	 * 根据id="checkAll"元素onclick事件中调用函数的参数【第二个参数即需要全选的复选框name值】 来获取需要全选的复选框对象
	 * 
	 * @param checkAllId
	 * @returns
	 */
	getCheckboxsByCheckAll : function(checkAllId) {
		try {
			//保证参数都是必须提供的
			if(checkAllId==undefined){
				this.debug('调用getCheckboxsByCheckAll()方法时提供的参数不够');
				return false;
			}
			var checkAll = $("#" + checkAllId);
			var str = checkAll.attr("onclick");
			var start = str.indexOf(",");
			var end = str.indexOf(",", start + 1);
			var checkboxName = str.substring(start + 2, end - 1);
			var checkboxs = $("input:[name=" + checkboxName
					+ "][type='checkbox']");
			return checkboxs;
		} catch (e) {
			this.debug('getCheckboxsByCheckAll()方法出现异常.' + e.message);
		}
	},

	/**
	 * 每次点击"子复选框"来判断是否让id="checkAll"的元素选中
	 * 
	 * @param checkAllId
	 */
	isCheckAll : function(checkAllId) {
		try {
			//保证参数都是必须提供的
			if(checkAllId==undefined){
				this.debug('调用isCheckAll()方法时提供的参数不够');
				return false;
			}
			var checkboxs = this.getCheckboxsByCheckAll(checkAllId);
			var flag = true;
			for ( var i = 0; i < checkboxs.length; i++) {
				if (!$(checkboxs[i]).is(':checked')) {
					flag = false;
					break;
				}
			}
			var checkAll = $("#" + checkAllId);
			checkAll.attr("checked", flag);
		} catch (e) {
			this.debug('isCheckAll()方法出现异常.' + e.message);
		}
	},

	/**
	 * 反选
	 * 
	 * @param checkboxName
	 *            子复选框的name值
	 */
	inverse : function(obj, checkboxName, checkAllName) {
		try {
			//保证参数都是必须提供的
			if(obj==undefined || checkboxName==undefined || checkAllName==undefined){
				this.debug('调用uncheckAll()方法时提供的参数不够');
				return false;
			}
			var flag = true;
			// 让子复选框进行反选,如果子复选框反选后全部选中，让全选框选中
			$("input:[name=" + checkboxName + "][type='checkbox']").each(
					function() {
						$(this).attr('checked', !$(this).is(':checked'));
						if (!$(this).is(':checked')) {
							flag = false;
						}
					});

			$("input:[name=" + checkAllName + "][type='checkbox']").each(
					function() {
						$(this).attr('checked', flag);
					});

		} catch (e) {
			this.debug('uncheckAll()方法出现异常.' + e.message);
		}
	},

	/**
	 * 让反选框不选中
	 * 
	 * @param uncheckAllId
	 *            反选复选框的id
	 */
	isUncheckAll : function(uncheckAllId) {
		try {
			//保证参数都是必须提供的
			if(uncheckAllId==undefined){
				this.debug('调用isUncheckAll()方法时提供的参数不够');
				return false;
			}
			var uncheckAll = $("#" + uncheckAllId);
			uncheckAll.attr("checked", false);
		} catch (e) {
			this.debug('isUncheckAll()方法出现异常.' + e.message);
		}
	},

	/**
	 * 获取复选框的选中个数和选中的值
	 * 
	 * @param checkboxName
	 *            //复选框的name
	 * @param rtype
	 *            //返回值的类型 1.值之间用逗号(,)隔开 2.值放在一个数组中 3.选中复选框的个数 4.返回1.2.3.三种的值
	 * @returns
	 */
	NumOrValues : function(checkboxName, rtype) {
		try {
			//保证参数都是必须提供的
			if(checkboxName==undefined || rtype==undefined){
				this.debug('调用NumOrValues()方法时提供的参数不够');
				return false;
			}
			var values = "";
			var array = new Array(); // 创建一个数组对象
			var length = 0;
			var arrays = new Array();
			$("input:[name=" + checkboxName + "][type='checkbox']").each(
					function() {
						if ($(this).is(':checked')) {
							values += "," + $(this).val();
							array.push($(this).val());
							length++;
						}
					});

			// 将第一个逗号(,)截取掉
			if (values) {
				values = values.substring(1, values.length);
			}

			arrays.push(values);
			arrays.push(array);
			arrays.push(length);

			switch (rtype) {
			case 1:
				return values;
				break;
			case 2:
				return array;
				break;
			case 3:
				return length;
				break;
			case 4:
				return arrays;
				break;
			default:
				this.debug('NumOrValues()方法中的返回值类型必须在【0,4】之间');
				return null;
			}
		} catch (e) {
			this.debug('NumOrValues()方法出现异常.' + e.message);
		}
	},

	/**
	 * 判断复选框是否选中
	 * 
	 * @param checkboxName
	 */
	isCheck : function(checkboxName) {
		try {
			//保证参数都是必须提供的
			if(checkboxName==undefined){
				this.debug('调用isCheck()方法时提供的参数不够');
				return false;
			}
			var flag = false;
			$("input:[name=" + checkboxName + "][type='checkbox']").each(
					function() {
						if ($(this).is(':checked')) {
							flag = true;
							return false;// 终止循环
						}
					});
			 return flag;
		} catch (e) {
			this.debug('ischeck()方法出现异常.' + e.message);
		}
	},

	/**
	 * 
	 * 在控制台输出日志       
	 * 
	 * @params message 要输出的日志信息       
	 */
	debug : function(message) {
		try {
			if (!this.isDebug) {
				return;
			}
			if (!window.console) {
				window.console = {};
				window.console.log = function() {
					return;
				};
			}
			window.console.log(message + ' ');
		} catch (e) {
		}
	}

};