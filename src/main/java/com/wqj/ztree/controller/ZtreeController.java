package com.wqj.ztree.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JSONHelper;
import com.wqj.ztree.SysMenuBean;
import com.wqj.ztree.entity.ZtreeMenu;
import jersey.repackaged.com.google.common.collect.Lists;

@Controller
@RequestMapping("/ztree")
public class ZtreeController extends CommonController {

	@RequestMapping("/simpleData")
	public String simpleData(HttpServletRequest request){
		List<ZtreeMenu> menus = ztreeService.findAll();
		
		List<SysMenuBean> list = Lists.newArrayList();
		SysMenuBean sysMenuBean = null;
		for (ZtreeMenu sysMenu : menus) {
			sysMenuBean = new SysMenuBean(sysMenu.getId(), sysMenu.getName(), 
					sysMenu.getpId()==null? 0L : sysMenu.getpId().getId(),sysMenu.getOpen(),
							sysMenu.getRank().getRandIcon().getIconOpen(),
							sysMenu.getRank().getRandIcon().getIconClose(),
							sysMenu.getRank().getRandIcon().getIcon(),sysMenu.getUrl());
			
			list.add(sysMenuBean);
		}
		
		/*{id:1, pId:0, name: "父节点1"},*/
		request.setAttribute("menuList", JSONHelper.toJson(list));
//		request.setAttribute("menuList", JSONHelper.toJson(menus));
		return "ztree/simpleData";
	}
	
	
	
	@RequestMapping("/excheck")
	public String excheck(HttpServletRequest request){
		List<ZtreeMenu> menus = ztreeService.findAll();
		
		List<SysMenuBean> list = Lists.newArrayList();
		SysMenuBean sysMenuBean = null;
		for (ZtreeMenu sysMenu : menus) {
			sysMenuBean = new SysMenuBean();
			sysMenuBean.setId(sysMenu.getId());
			sysMenuBean.setName(sysMenu.getName());
			sysMenuBean.setpId(sysMenu.getpId()==null? 0L : sysMenu.getpId().getId());
			sysMenuBean.setIconOpen(sysMenu.getRank().getRandIcon().getIconOpen());
			sysMenuBean.setIconClose(sysMenu.getRank().getRandIcon().getIconClose());
			sysMenuBean.setIcon(sysMenu.getRank().getRandIcon().getIcon());
			sysMenuBean.setUrl(sysMenu.getUrl());
			list.add(sysMenuBean);
		}
			
		request.setAttribute("menuList", JSONHelper.toJson(list));
		return "ztree/excheck";
	}
	@RequestMapping("/outLook")
	public String outLook(HttpServletRequest request){
		List<ZtreeMenu> menus = ztreeService.findAll();
		
		List<SysMenuBean> list = Lists.newArrayList();
		SysMenuBean sysMenuBean = null;
		for (ZtreeMenu sysMenu : menus) {
			sysMenuBean = new SysMenuBean();
			sysMenuBean.setId(sysMenu.getId());
			sysMenuBean.setName(sysMenu.getName());
			sysMenuBean.setpId(sysMenu.getpId()==null? 0L : sysMenu.getpId().getId());
			sysMenuBean.setIconOpen(sysMenu.getRank().getRandIcon().getIconOpen());
			sysMenuBean.setIconClose(sysMenu.getRank().getRandIcon().getIconClose());
			sysMenuBean.setIcon(sysMenu.getRank().getRandIcon().getIcon());
			sysMenuBean.setUrl(sysMenu.getUrl());
			list.add(sysMenuBean);
		}
		request.setAttribute("menuList", JSONHelper.toJson(list));
		return "ztree/outLook";
	}
	
}
