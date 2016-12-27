jQuerySelectUtils = {
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
	 * 清空select中的option
	 * 
	 * @param selectId
	 * @returns
	 */
	empty : function(selectId) {
		try {
			if (this.isNotUndefined(selectId)) {
				$("#" + selectId).empty(); // 清空select中的option
			} else {
				this.debug('empty()方法参数不够');
			}
		} catch (e) {
			this.debug('empty()方法有错误' + e.message);
		}
	},

	/**
	 * 为Select添加事件，当选择其中的option时触发
	 * 
	 * @param selectId
	 * @returns
	 */
	optionChange : function(selectId) {
		try {
			if (this.isNotUndefined(selectId)) {
				$("#" + selectId).change(function() {
					var currentVal = $(this).val(); // 得到当前选中的值
					changeAfter(currentVal);
				});
			} else {
				this.debug('optionChange()方法参数不够');
			}
		} catch (e) {
			this.debug('optionChange()方法有错误' + e.message);
		}
	},

	/**
	 * 获取下拉选项中选中的值
	 * 
	 * @param selectId
	 * @returns
	 */
	selectedVal : function(selectId) {
		try {
			if (this.isNotUndefined(selectId)) {
				// 方式一：var selectedValue=$("#"+selectId).val();
				// 方式二： var
				// selectedValue=$("#"+selectId).find('option:selected').val();
				var selectedValue = $('#' + selectId + ' option:selected')
						.val();
				return selectedValue;
			} else {
				this.debug('optionChange()方法参数不够');
			}
		} catch (e) {
			this.debug('selectedVal()方法有错误' + e.message);
		}
	},
	
	/**
	 * 获取下拉选项中选中的文本
	 * 
	 * @param selectId
	 * @returns
	 */
	selectedText : function(selectId) {
		try {
			if (this.isNotUndefined(selectId)) {
				// 方式一：var selectedText=$("#"+selectId).text();
				// 方式二： var
				// selectedText=$("#"+selectId).find('option:selected').text();
				var selectedText = $('#' + selectId + ' option:selected')
						.text();
				return selectedText;
			} else {
				this.debug('optionChange()方法参数不够');
			}
		} catch (e) {
			this.debug('selectedText()方法有错误' + e.message);
		}
	},
	
	/**
	 * 为select在最后追加一个option
	 */
	append : function(selectId, value, text) {
		try {
			if (this.isUndefined(value)) {
				value = -99;
			}
			if (this.isUndefined(text)) {
				text = '-不限-';
			}
			if (this.isNotUndefined(selectId)) {
				// 方式一： $("#"+selectId).append($("<option
				// value='"+value+"'>"+text+"</option>"));
				$("<option/>").html(text).val(value).appendTo("#" + selectId);
			} else {
				this.debug('optionChange()方法参数不够');
			}
		} catch (e) {
			this.debug('append()方法有错误' + e.message);
		}
	},

	/**
	 * 为select在第一个位置插入一个option
	 */
	prepend : function(selectId, value, text) {
		try {
			if (this.isUndefined(value)) {
				value = -99;
			}
			if (this.isUndefined(text)) {
				text = '-不限-';
			}
			if (this.isNotUndefined(selectId)) {
				$("#" + selectId)
						.prepend(
								$("<option value='" + value + "'>" + text
										+ "</option>"));
			} else {
				this.debug('optionChange()方法参数不够');
			}
		} catch (e) {
			this.debug('prepend()方法有错误' + e.message);
		}
	},

	/**
	 * 根据给定的json数据添加option选项
	 * 
	 * @param data,
	 *            selectId, hintVal, hintHtml
	 * @returns
	 */
	addTo : function(data, selectId, hintVal, hintHtml) {
		try {
			if (this.isUndefined(hintVal)) {
				hintVal = -99;
			}
			if (this.isUndefined(hintHtml)) {
				hintHtml = '请选择';
			}
			if (this.isNotUndefined(data) && this.isNotUndefined(selectId)) {
				// ----------------------核心部分开始----------------------------
				if (data.length > 0) {
					$("#" + selectId).append(
							$("<option value='" + hintVal + "'>-" + hintHtml
									+ "-</option>"));
					for ( var i = 0; i < data.length; i++) {
						$("#" + selectId).append(
								$("<option value=" + data[i].id + ">"
										+ data[i].name + "</option>"));
					}
				} else {
					$("#" + selectId)
							.append($("<option value=''>-无-</option>"));
				}
				// ----------------------核心部分结束----------------------------
			} else {
				this.debug('addTo()方法参数不够');
			}
		} catch (e) {
			this.debug('addTo()方法有错误' + e.message);
		}

	},

	/**
	 * 判断一个对象是否未定义
	 */
	isNotUndefined : function(obj) {
		try {
			if (obj != undefined) {
				return true;
			}
			return false;
		} catch (e) {
			this.debug('isNotUndefined()方法有错误' + e.message);
		}
	},

	/**
	 * 判断一个对象是否未定义
	 */
	isUndefined : function(obj) {
		try {
			if (obj == undefined) {
				return true;
			}
			return false;
		} catch (e) {
			this.debug('isUndefined()方法有错误' + e.message);
		}
	},

	/**
	 * 不可用
	 */
	disabled : function(selectId) {
		try {
			if (this.isNotUndefined(selectId)) {
				// 方式一；$('#' + selectId).attr("disabled", "disabled");
				// 方式二：$('#' + selectId).attr("disabled", "");
				$('#' + selectId).attr("disabled", true);
			} else {
				this.debug('disabled()方法参数不够');
			}
		} catch (e) {
			this.debug('disabled()方法有错误' + e.message);
		}
	},

	/**
	 * 可用
	 */
	undisabled : function(selectId) {
		try {
			if (this.isNotUndefined(selectId)) {
				// 方式一：$('#' + selectId).attr("disabled", false);
				$("select").removeAttr("disabled");
			} else {
				this.debug('undisabled()方法参数不够');
			}
		} catch (e) {
			this.debug('undisabled()方法有错误' + e.message);
		}
	},

	/**
	 * 判断是否选中
	 */
	isSelected : function(selectId) {
		try {
			if (this.isNotUndefined(selectId)) {
				var selectedValue = this.selectedVal(selectId);
				if (selectedValue == '' || selectedValue == -99) {
					return false;
				}
				return true;
			} else {
				this.debug('undisabled()方法参数不够');
			}
		} catch (e) {
			this.debug('undisabled()方法有错误' + e.message);
		}
	},
	
	/**
	 * 让某个option选中
	 * 
	 * @param selectId
	 * @param value
	 */
	selectedByValue:function(selectId,value){
		if (this.isNotUndefined(selectId) && this.isNotUndefined(value)) {
			$("#"+selectId).children("option").each(function(){
				if($(this).val()==value){
					$(this).attr("selected",true);
					return false;
				}
			});
		} else {
			this.debug('undisabled()方法参数不够');
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