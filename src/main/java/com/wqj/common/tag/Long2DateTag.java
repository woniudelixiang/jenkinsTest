package com.wqj.common.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import com.wqj.common.util.DateUtils;

@SuppressWarnings("serial")
public class Long2DateTag extends TagSupport {
	private Long value;
	private String pattern = DateUtils.PATTERN_DATETIME;

	@Override
	public int doStartTag() throws JspException {
		System.out.println("************  doStartTag()  **************");
		//return SKIP_BODY;
		return	EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		System.out.println("************  doEndTag()  **************");
		JspWriter out = pageContext.getOut();
		try {
			out.print(DateUtils.long2DateString(value, pattern));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
		//return SKIP_PAGE;
	}

	@Override
	public int doAfterBody() throws JspException {
		System.out.println("************  doAfterBody()  **************");
		return SKIP_BODY;
	}

	/**
	 * 1.处理标签的类必须扩展javax.servlet.jsp.TagSupport. 2.TagSupport类的主要属性：
	 * A.parent属性：代表嵌套了当前标签的上层标签的处理类
	 * B.pageContex属性：代表Web应用中的javax.servlet.jsp.PageContext对象
	 * 3.JSP容器在调用doStartTag或者doEndTag方法前
	 * ，会先调用setPageContext和setParent方法，设置pageContext和parent
	 * 。因此在标签处理类中可以直接访问pageContext变量
	 * 4.在TagSupport的构造方法中不能访问pageContext成员变量，因为此时JSP容器还没有调用
	 * setPageContext方法对pageContext进行初始化
	 * 
	 */

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
