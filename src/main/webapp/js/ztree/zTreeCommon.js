var ZTCommon = function() {
	
	var curMenu = null, zTree_Menu = null;
	
	return {
		// zTree公共参数设置
		option : function(args) {
			var defaults = {
//					check: {
//						enable: true,   // 节点上是否显示 checkbox / radio
//						chkStyle: "radio",  // 勾选框类型
//						radioType: "level"	// radio 的分组范围
//					},
					
					view: {
						showLine: true,  // 是否显示连接线 
						showIcon: this.showIconForTree,  // 是否显示节点的图标
						dblClickExpand: true,   // 双击切换展开状态
						fontCss: this.setFontCss,
					},
					
					data : {
						simpleData : {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					},
					
					callback: {
						beforeClick: this.beforeClick,
						onClick: this.onClick,
						onNodeCreated: this.zTreeOnNodeCreated,   // 节点生成 DOM 后的事件回调函数
					}
			};

			args = $.extend(true, defaults, args);
			return args;
		},
		
		// 初始化zTree插件
		init : function(obj, zSetting, zNodes){
			$.fn.zTree.init($("#"+obj), zSetting, zNodes);
			
			zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			curMenu = zTree_Menu.getNodes()[0];//.children[0].children[0];
			zTree_Menu.selectNode(curMenu);
			
			var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
			a.addClass("cur");
		},
		
		// 是否显示节点的图标逻辑处理方法
		showIconForTree : function (treeId, treeNode) {
			// return !treeNode.isParent;
			return true;
		},
		
		setFontCss : function (treeId, treeNode) {
			  return treeNode.level % 2 == 0 ? { 
				  'color': 'red',
				  'width': '200px',
				  'height': '20px',
				   'text-align': 'center',
					'display' : 'block',
					'background-color' : '#66A3D2',
					 'border': '1px silver solid',
			  } : {};
		},
		
		zTreeOnNodeCreated: function (event, treeId, treeNode) {
			console.log(treeNode);
		    console.log(treeNode.tId + ", " + treeNode.name +"  : " + treeNode.level);
		    if(treeNode.level === 0){
		    	console.log(treeNode.nocheck);
		    	treeNode.nocheck = true; // 设置节点是否隐藏 checkbox / radio 
		    }
		},

		beforeClick : function (treeId, node) {
			console.log("点击前触发");
			if (node.isParent) {
				console.log("node.isParent： " + node.isParent);
				if (node.level === 0) {
					console.log("node.level=== 0： " + (node.level=== 0));
					var pNode = curMenu;
					while (pNode && pNode.level !==0) {
						pNode = pNode.getParentNode();
					}
					
					console.log("pNode.name : " + pNode.name +",     node.name: " + node.name);
					
					
					if (pNode !== node) {  //  二个主菜单之间的切换
						console.log("二个主菜单之间的切换");
						var a = $("#" + pNode.tId + "_a");
						a.removeClass("cur");
						zTree_Menu.expandNode(pNode, false);
					}
					a = $("#" + node.tId + "_a");
					a.addClass("cur");

					var isOpen = false;
					for (var i=0,l=node.children.length; i<l; i++) {
						if(node.children[i].open) {
							isOpen = true;
							break;
						}
					}
					
					console.log("isOpen: " + isOpen);
					if (isOpen) {
						zTree_Menu.expandNode(node, true);
						curMenu = node;
					} else {
						zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
						curMenu = node.children[0];
					}
				} else {
					zTree_Menu.expandNode(node);
				}
			}
			return !node.isParent;
		},
		
		onClick : function (e, treeId, node) {
			console.log("Do what you want to do...!");
		}

	};
}();
window.$ZTC = ZTCommon;