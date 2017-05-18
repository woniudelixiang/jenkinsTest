package com.wqj.forms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.annotation.FormToken;
import com.wqj.common.controller.CommonController;
import com.wqj.common.utils.TokenProcessor;
import com.wqj.forms.pojo.ConditionData;

@Controller
@RequestMapping("/forms")
public class FormsController extends CommonController {

	/**
	 * 防止表单重复提交
	 * @param cond
	 * @param request
	 * @return
	 */
	@RequestMapping("/resubmitUI")
	public String resubmitUI(HttpServletRequest request) throws Exception {
		TokenProcessor.getInstance().saveToken(request);
		return "/forms/resubmit/list";
	}
	
//	@RequestMapping("/resubmit")
//	public String resubmit(ConditionData cond, HttpServletRequest request, HttpSession session) throws Exception {
//		TokenProcessor token = TokenProcessor.getInstance();
//		boolean tokenValid = token.isTokenValid(request);
//		System.out.println("tokenValid: " + tokenValid);
//		Thread.sleep(1000);
//		if(tokenValid){
//			token.resetToken(request);
//			System.out.println("====>>>"+cond.toString());
//			Thread.sleep(1000);
//		}else{
//			System.out.println("1表单重复提交了........");
//		}
//		return redirect("resubmitUI");
//	}
	
//	@RequestMapping("/resubmit")
//	public String resubmit(ConditionData cond, HttpServletRequest request, HttpSession session) throws Exception {
//		TokenProcessor token = TokenProcessor.getInstance();
//		boolean tokenValid = token.isTokenValid(request);
//		System.out.println("tokenValid: " + tokenValid);
//		Thread.sleep(1000);
//		if(tokenValid){
//			synchronized(this){
//				tokenValid = token.isTokenValid(request);
//				if(tokenValid){
//					token.resetToken(request);
//					System.out.println("====>>>"+cond.toString());
//					Thread.sleep(1000);
//				}else{
//					System.out.println("2表单重复提交了........");
//				}
//			}
//		}else{
//			System.out.println("1表单重复提交了........");
//		}
//		return redirect("resubmitUI");
//	}
	
	@RequestMapping("/resubmit")
	public String resubmit(ConditionData cond, HttpServletRequest request, HttpSession session) throws Exception {
		TokenProcessor token = TokenProcessor.getInstance();
		boolean tokenValid = token.isTokenValid(request);
		if(tokenValid){
			synchronized(this){
				tokenValid = token.isTokenValid(request);
				if(tokenValid){
					token.resetToken(request);
					System.out.println("====>>>"+cond.toString());
				}else{
					System.out.println("2表单重复提交了........");
				}
			}
		}else{
			System.out.println("1表单重复提交了........");
		}
		return redirect("resubmitUI");
	}
	
	@RequestMapping("/tokenHelperUI")
	public String tokenHelperUI(HttpServletRequest request) throws Exception {
		return "/forms/resubmit/list_tokenHelper";
	}
	
	@RequestMapping("/tokenHelper")
	@FormToken
	public String tokenHelper(ConditionData cond, HttpServletRequest request, HttpSession session) throws Exception {
		Thread.sleep(1000*5);
		System.out.println("执行controller中的代码了....................");
		return redirect("list");
	}
	
	@RequestMapping("/list")
	public String list(ConditionData cond, HttpServletRequest request) {
		request.setAttribute("cond", cond);
		return "forms/list";
	}
	
	@RequestMapping("/toEdit/{id}")
	public String toEdit( @PathVariable Long id, ConditionData cond, HttpServletRequest request) {
		request.setAttribute("cond", cond);
		return "forms/edit";
	}
	
	@RequestMapping("/edit")
	public String edit(ConditionData cond, HttpServletRequest request) {
		request.setAttribute("cond", cond);
		return forward("/list");
	}


}
