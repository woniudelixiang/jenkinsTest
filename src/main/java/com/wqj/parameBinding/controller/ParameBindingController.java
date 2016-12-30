package com.wqj.parameBinding.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.common.util.DateUtils;
import com.wqj.common.util.ValidateUtils;
import com.wqj.parameBinding.entity.Student;
import com.wqj.parameBinding.entity.User;
import com.wqj.parameBinding.entity.XML;
import com.wqj.parameBinding.pojo.AnnotationFormatterBean;
import com.wqj.parameBinding.pojo.LevelMapData;
import com.wqj.parameBinding.pojo.UserListData;
import com.wqj.parameBinding.pojo.UserMapData;
import com.wqj.parameBinding.pojo.UserSetData;

//参数绑定
@Controller
@RequestMapping("/parameBinding")
public class ParameBindingController extends CommonController {
	
	// 基本数据类型   http://localhost:8080/dao-study/parameBinding/baseType?ages=10
	@RequestMapping("/baseType")
	@ResponseBody
	public String baseType(@RequestParam(value="ages" , required=false,defaultValue = "0")int age) {
		return "age = " + age;
	}
	
	// 包装类类型  http://localhost:8080/dao-study/parameBinding/packType?ages=10
	@RequestMapping("/packType")
	@ResponseBody
	public String packType(Integer age) {
		return "age = " + age;
	}
	
	// 数组    http://localhost:8080/dao-study/parameBinding/arrayType?arrayStr=1&arrayStr=2
	@RequestMapping("/arrayType")
	@ResponseBody
	public String arrayType(String [] arrayStr) {
		StringBuffer sb  = new StringBuffer();
		if(arrayStr!=null){
			for (String str : arrayStr) {
				sb.append(str).append(" ");
			}
		}
		return sb.toString();
	}
	
	// 简单对象    http://localhost:8080/dao-study/parameBinding/simpleObject?name=wqj&age=24
	@RequestMapping("/simpleObject")
	@ResponseBody
	public String simpleObject(User user) {
		return user.toString();
	}
	
	// 多层次对象     http://localhost:8080/dao-study/parameBinding/levelObject?name=wqj&age=24&contactInfo.phone=123456789&contactInfo.address=nj
	@RequestMapping("/levelObject")
	@ResponseBody
	public String levelObject(User user) {
		return user.toString();
	}
	
	// 同属性多对象    http://localhost:8080/dao-study/parameBinding/sameAttrMoreObj?u.name=wqj&s.name=clx&age=24
	@RequestMapping("/sameAttrMoreObj")
	@ResponseBody
	public String sameAttrMoreObj(User user,Student student) {
		return user.toString() +"   ,   "+ student.toString();
	}
	
	@InitBinder("user")
	public void initUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("u.");
	}
	
	@InitBinder("student")
	public void initStudent(WebDataBinder binder){
		binder.setFieldDefaultPrefix("s.");
	}
	
	// List
	//http://localhost:8080/dao-study/parameBinding/list?userList[0].name=wqj&userList[0].age=24&userList[1].name=clx&userList[1].age=23
	//http://localhost:8080/dao-study/parameBinding/list?userList[0].name=wqj&userList[0].age=24&userList[20].name=clx&userList[20].age=23
	@RequestMapping("/list")
	@ResponseBody
	public String list(UserListData userListData) {
		System.out.println("listSize:" + userListData.getUserList().size());
		return userListData.toString();
	}
	
	// Set
	// http://localhost:8080/dao-study/parameBinding/set?userSet[0].name=wqj&userSet[0].age=24&userSet[1].name=clx&userSet[1].age=23
	@RequestMapping("/set")
	@ResponseBody 
	public String set(UserSetData userSetData) {
		System.out.println("setSize:" + userSetData.getUserSet().size());
		return userSetData.toString();
	}
	
	
	// Map   http://localhost:8080/dao-study/parameBinding/map?userMap["X"].name=wqj&userMap["X"].age=24&userMap["Y"].name=clx&userMap["Y"].age=23
	@RequestMapping("/map")
	@ResponseBody
	public String map(UserMapData userMapData) {
		return userMapData.toString();
	}
	
	// Map   http://localhost:8080/dao-study/parameBinding/map?userMap["contactInfo.phone"]=18351458870&userMap["contactInfo.address"]=ah
	@RequestMapping("/levelMap")
	@ResponseBody
	public String levelMap(LevelMapData levelMapData) {
		return levelMapData.toString();
	}
	
	
	
	
	
	// JSON  
	/* http://localhost:8080/dao-study/parameBinding/json
	 * {
	 * 	  "name": "wqj",
	 * 	  "age": 24,
	 *    "contactInfo": {
	 * 	       "phone": "123456789",
	 * 	       "address": "nj"
	 * 	    }
	 * 	}
	 */
	@RequestMapping("/json")
	@ResponseBody
	public String json(@RequestBody User user) {
		return user.toString();
	}
	
	// xml
	/* http://localhost:8080/dao-study/parameBinding/xml
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *	<xmlRoot>
	 *		<name>wqj</name>
	 *		<age>24</age>
	 *	</xmlRoot>
	 */
	@RequestMapping("/xml")
	@ResponseBody
	public String xml(@RequestBody XML xml) {
		return xml.toString();
	}
	
	//日期类型的参数绑定（局部）
	@RequestMapping("/date")
	@ResponseBody
	public String date(Date date) {
		return date.toString();
	}
	
	/*@InitBinder("date")
	public void initDate(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}*/
	
	//日期类型的参数绑定（局部）
	@RequestMapping("/formatterDate")
	@ResponseBody
	public String formatterDate(Date date) {
		String str = "";
		if(ValidateUtils.isNotEmpty(date)){
			str = date.toString();
		}
		return str;
	}
	
	//日期类型的参数绑定（局部）
	@RequestMapping("/converterDate")
	@ResponseBody
	public String converterDate(Date date) {
		String str = "";
		if(ValidateUtils.isNotEmpty(date)){
			str = date.toString();
		}
		return str;
	}
	
	//日期类型的参数绑定（局部）
	@RequestMapping("/formatterStr2Long")
	@ResponseBody
	public String formatterStr2Long(AnnotationFormatterBean formatterBean) {
		String startTime = DateUtils.long2LongString(formatterBean.getStartTime());
		String endTime = DateUtils.long2LongString(formatterBean.getEndTime());
		return startTime + "  <-------------> " + endTime +" <-----------> " + formatterBean.getVersion();
	}
	
}
