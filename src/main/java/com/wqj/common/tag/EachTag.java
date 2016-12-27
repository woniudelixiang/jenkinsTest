package com.wqj.common.tag;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class EachTag  extends TagSupport{
	
	/**
     * 反序列化
     */
    private static final long serialVersionUID = 7239081890975877992L;
 
    private String var;
    private Iterator<Object> it;
 
    public void setVar(String var) {
        this.var = var;
    }
    
   
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void setItems(Object object) {
    	System.out.println("***********setItems************"+object.toString());
        if (object instanceof Collection) {
            it = ((Collection) object).iterator();
        } else if (object instanceof Map) {
            it = ((Map) object).entrySet().iterator();
        } else if (object.getClass().isArray()) {//数组反射，虽然影响了效率，但是比每种类型相比是简单多了
            List<Object> list = new ArrayList<Object>();
            int len = Array.getLength(object);
            for (int i = 0; i < len; i++) {
                list.add(Array.get(object, i));
            }
            it = list.iterator();
        }
    }
 
    // 只会执行一次
    @Override
    public int doStartTag() throws JspException {
    	System.out.println("********   开 始    *********");
        if (it.hasNext()) {
            pageContext.setAttribute(var, it.next());
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
 
    @Override
    public int doAfterBody() throws JspException {
    	System.out.println("********  doAfterBody    *********");
        // TODO Auto-generated method stub
        if (it.hasNext()) {
            pageContext.setAttribute(var, it.next());
            return EVAL_BODY_AGAIN;// 再次执行body部分
        } else {
            return SKIP_BODY;
        }
 
    }
 
    @Override
    public int doEndTag() throws JspException {
    	System.out.println("********  doEndTag    *********");
        pageContext.removeAttribute(var);
        return EVAL_PAGE;
    }
}
