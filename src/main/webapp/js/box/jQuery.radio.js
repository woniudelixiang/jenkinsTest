jQueryRadioUtils = {
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
	 * 获取选中radio的值
	 */
	value : function(radioName) {
		var value;
		try {
			if (this.isUndefined(radioName)) {
				this.debug('value()方法参数不够.');
			} else {
				// 方式一： var value = $("input[name=" + radioName +
				// "][type='radio']:checked").val();
				// 方式二： var value=$('input:radio:checked').val();
				// 方式四： var
				// value=$('input[@name="+radioName+"][@type="radio"][checked]').val();
				value = $('input[name=' + radioName + '][type="radio"]')
						.filter(':checked').val();
			}
		} catch (e) {
			this.debug('value()方法出现异常.' + e.message);
		}
		return value;
	},

	/**
	 * 为radio添加事件，当选择其中一项时触发
	 * 
	 * @param selectId
	 * @returns
	 */
	radioclick : function(radioName) {
		try {
			if (this.isNotUndefined(radioName)) {
				$('input[name=' + radioName + '][type="radio"]').change(
						function() {// 这里将change改变成click一样效果
							var currentVal = $(this).val(); // 得到当前选中的值
							radioClickAfter(currentVal);
						});
			} else {
				this.debug('radioclick()方法参数不够');
			}
		} catch (e) {
			this.debug('radioclick()方法有错误' + e.message);
		}
	},

	/**
	 * 判断单选框是否选中
	 * 
	 * @param checkboxName
	 */
	isChecked : function(radioName) {
		try {
			// 保证参数都是必须提供的
			if (this.isUndefined(radioName)) {
				this.debug('调用isChecked()方法时提供的参数不够');
				return false;
			}
			var checkedObj = $('input[name=' + radioName + '][type="radio"]')
					.filter(':checked');

			if (checkedObj.length == 0) {
				return false;
			} else {
				return true;
			}
		} catch (e) {
			this.debug('isChecked()方法出现异常.' + e.message);
		}
	},

	/**
	 * 让某个单选按钮选中
	 * 
	 * @param selectId
	 * @param value
	 */
	selectedByValue : function(radioName, value) {
		try {
			if (this.isNotUndefined(radioName) && this.isNotUndefined(value)) {
				$('input[name=' + radioName + '][type="radio"]').each(
						function() {
							if ($(this).val() == value) {
								$(this).attr("checked", true);
								return false;//退出循环
							};
						});
			} else {
				this.debug('undisabled()方法参数不够');
			}
		} catch (e) {
			this.debug('selectedByValue()方法出现异常.' + e.message);
		}
	},
	
	/**
	 * 全不选中
	 * 
	 * @param selectId
	 */
	unAllSelected : function(radioName) {
		try {
			if (this.isNotUndefined(radioName)) {
				$('input[name=' + radioName + '][type="radio"]').each(
						function() {
							if ($(this).attr("checked")) {
								$(this).attr("checked", false);
							}
						});
			} else {
				this.debug('unAllSelected()方法参数不够');
			}
		} catch (e) {
			this.debug('unAllSelected()方法出现异常.' + e.message);
		}
	},

	/**
	 * 禁用
	 * 
	 * @param checkboxName
	 */
	disable : function(radioName) {
		try {
			$('input[name=' + radioName + '][type="radio"]').each(function() {
				$(this).attr('checked', false);
				// $(this).attr('disabled', 'disabled');
				// $(this).attr('disabled',true);
				$(this).attr('disabled', '');
			});
		} catch (e) {
			this.debug('disable()方法出现异常.' + e.message);
		}
	},

	/**
	 * 启用
	 * 
	 * @param checkboxName
	 */
	enable : function(radioName) {
		try {
			$('input[name=' + radioName + '][type="radio"]').each(function() {
				// $(this).attr('disabled',false);
				$(this).removeAttr('disabled');
			});
		} catch (e) {
			this.debug('enable()方法出现异常.' + e.message);
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