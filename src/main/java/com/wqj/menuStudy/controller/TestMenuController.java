package com.wqj.menuStudy.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.wqj.common.controller.CommonController;
import com.wqj.menuStudy.entity.Menu;
import com.wqj.menuStudy.service.TestMenuService;

@Controller
@RequestMapping("/testMenu")
public class TestMenuController extends CommonController{
	@Autowired
	protected TestMenuService testMenuService;
	
	@RequestMapping("/left")
	public String leftByEmployee(HttpSession session, Model model) {
		List<Menu> rootMenus = testMenuService.getRootMenus();
		List<Menu> menus = testMenuService.loopQueryMenusByParent(rootMenus);
		List<Object> list = Lists.newArrayList();
		List<Object> objs = loop(list, (List)menus, 0);
		for(Object obj : objs){
			com.wqj.menuStudy.entity.Menu m= (com.wqj.menuStudy.entity.Menu)obj;
			System.out.println(m.toString());
		}
		session.setAttribute("menus", menus);
		return diffRedirect("/main/testMenu.jsp");
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> loop(List<Object> list, List<Object> nodes, int rows) {
		for (int i = 0; i < nodes.size(); i++) {
			Object node = nodes.get(i);
			try {
				Method setLevel = node.getClass().getMethod("setLevel",
						int.class);
				setLevel.invoke(node, rows);

				Method setRows = node.getClass().getMethod("setRows",
						new Class[] { String[].class });
				setRows.invoke(node, new Object[] { new String[rows] });
				list.add(node);
				if (i == 0) {
					Method setFirst = node.getClass().getMethod("setFirst",
							boolean.class);
					setFirst.invoke(node, Boolean.TRUE);
				}
				if (i == nodes.size() - 1) {
					Method setLast = node.getClass().getMethod("setLast",
							boolean.class);
					setLast.invoke(node, Boolean.TRUE);
				}
				Method getChild = node.getClass().getMethod("getChild");
				List<Object> childs = (List<Object>) getChild.invoke(node);

				Method getExpanded = node.getClass().getMethod("getExpanded");
				Boolean expanded = (Boolean) getExpanded.invoke(node);

				if (CollectionUtils.isNotEmpty(childs)
						&& BooleanUtils.isTrue(expanded)) {
					loop(list, childs, rows + 1);
				}
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return list;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("{id}/menu")
	public String menu(@PathVariable long id, HttpSession session) {
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		testMenuService.handle(id, menus);
		session.setAttribute("menus", menus);
		return diffRedirect("/main/testMenu.jsp");
	}
	
}
