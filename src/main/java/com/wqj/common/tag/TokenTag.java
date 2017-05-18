package com.wqj.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wqj.common.helper.TokenHelper;

@SuppressWarnings("serial")
public class TokenTag extends TagSupport {

	 public int doStartTag() throws JspException {   
        JspWriter out = pageContext.getOut();
        try{
            out.println("<input type=\"hidden\" name=\"" + TokenHelper.TOKEN_KEY + "\" value=\"" + TokenHelper.setToken(pageContext.getSession()) + "\"/>");
        }catch(IOException e){   
            throw new JspException(e);   
        }
        return SKIP_BODY; 
	 }
}
