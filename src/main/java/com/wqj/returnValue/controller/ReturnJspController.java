package com.wqj.returnValue.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Maps;
import com.wqj.common.controller.CommonController;
import com.wqj.returnValue.entity.User;

import ch.qos.logback.core.net.SyslogOutputStream;
import jersey.repackaged.com.google.common.collect.Lists;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/returnJsp")
/*@SessionAttributes(value={"message"})*/
public class ReturnJspController extends CommonController {
	
	@RequestMapping("/sendRedirect")
	public ModelAndView sendRedirect(HttpServletResponse response) throws IOException {
		String message = "这个是要传递给jsp的数据";  // 需要带到jsp页面的附加数据
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(message);
		writer.flush();
		writer.close();

		ModelAndView mv = new ModelAndView("redirect:/returnJsp/toSend","message",message);
		return mv;
	}
	
	@RequestMapping("/toSend")
	public String toSend(HttpServletRequest request) {
		System.out.println("~~~~~~~~~~~~~~~~~~~   toSend    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		return "returnJsp/modelAndView";
	}
	
	
	// 返回ModelAndView对象
	@RequestMapping("/modelAndView")
	public ModelAndView resultModelAndView1() {
		String message = "这个是要传递给jsp的数据";  // 需要带到jsp页面的附加数据
		
		/*
		 * 其中第一个参数为url,第二个参数为要传递的数据的key,第三个参数为数据对象。在这里要注意的是   数据是默认被存放在request中的。
		 * 可以在类的前面添加注解@SessionAttributes将model中参数在session中存储一份
		*/
		// ModelAndView mv = new ModelAndView("returnJsp/modelAndView","message",message);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", message);  // 向页面传递的数据
		/* 
		 * 返回视图的名称   如果没有指定返回的视图名，就响应相应的url	例如：请求为 ${appName}/${conName}/${funName}  
		 * 则响应的url为  /${conName}/${funName} 
		 * 在视图解析器解析之后得到的最终的url为   /WEB-INF/pages/${conName}/${funName}.jsp
		 * 注意：如果方法名中有特殊的字符<如：.>将截取特殊字符前面的字符串为时的名称，
		 * */
		mv.setViewName("returnJsp/modelAndView");
		
		/*
		 * 可以通过ModelAndView的  mv.addObject(attributeName, attributeValue) 方法设置多个返回数据。
		 * 第一个参数为数据的key，第二个参数为数据对象,
		 * 其中第一个参数可以省略，如果省略，数据的key为该数据对象的类名（其中首字母小写）。 其中基本数据类型是对应包装类
		 * */
		mv.addObject(new User("王启军",1)); //  key = user
		mv.addObject("abc"); // key = string
		
		// mv.addObject() 相当于request.setAttribute方法，所以向ModelAndView对象中添加数据时，如果key值相同，则后添加的会覆盖之前的
		mv.addObject("efg");
		return mv;
	}
	
	// 返回String对象
	@RequestMapping("/string")
	public String resultString(ModelMap model) {
		String message = "这个是要传递给jsp的数据";  // 需要带到jsp页面的附加数据
		// 将数据存入modelMap
		model.addAttribute("message", message);
		// 将数据存入modelMap
		model.put("msg", message);
		//返回视图的名称
		return "returnJsp/string";
	}
	
	// 返回void
	@RequestMapping("/void")
	public void resultVoid(ModelMap model) {
		String message = "这个是要传递给jsp的数据";  // 需要带到jsp页面的附加数据
		// 将数据存入modelMap
		model.addAttribute("message", message);
		// 将数据存入modelMap
		model.put("msg", message);
	}
	@SuppressWarnings("unchecked")
	
	// 返回List对象
	@RequestMapping("/list")
	public List resultList(){
		List list = Lists.newArrayList();
		/*
		 * 当返回值为List时，响应相应的url,spring会将list对象存储在request中，
		 * 而该对象存储的key为：XXXList ，
		 * 其中XXX表示list中第一个元素的类名(如果是基本数据类型就是包装类类名) 并且首字母小写
		 */
		// 由于list中的第一个为User，所以 key = userList
		/*list.add(new User("王启军",1));
		list.add(1);*/
		
		// 由于list中的第一个为1（Integer类型），所以key = integerList
		list.add(1);
		list.add(new User("王启军",1));
		
		return list;
	}
	
	// 返回Set对象
//	@RequestMapping("/set")
//	public Set<User> setFunction(){
//		Set<User> set = Sets.newHashSet();
//		set.add(new User("王启军",1));
//		Iterator iterator = set.iterator();
//		System.out.println(iterator.next());
//		//http://www.runoob.com/
//		/*set.add(new User("王启军",1));*/
//		/*request.setAttribute("userSet", set);*/
//		return set;
//	}
	
	
	// 返回Map对象
	@RequestMapping("/map")
	public Map<String, String> resultMap() { 
		Map<String, String> map = Maps.newHashMap();
		map.put("key1", "value-1"); 
		map.put("key2", "value-2"); 
		/*
		 * map.put() 相当于request.setAttribute方法，
		 * 在页面中直接可以通过${key1 }， ${key2 } 获取数据
		 */
		return map; 
	}
	
	// 返回ModelMap对象
	@RequestMapping(value="/modelMap")
	public ModelMap resultModelMap(ModelMap map){
		map.put("msg", "这里是modleMap中的数据");
		/*
		 * map.put() 相当于request.setAttribute方法，
		 * 在页面中直接可以通过${key1 }， ${key2 } 获取数据
		 */
		return map;
	}

	// 返回Object对象
	@RequestMapping(value="/object")
	public User resultObject(){
		return new User("张四",1);
		/*
		 * 由于返回的是User对象，所以key = user，在前台可以通过
		 * ${user.username }获取username
		 */
	}
	
}
