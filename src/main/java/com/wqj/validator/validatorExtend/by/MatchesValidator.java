package com.wqj.validator.validatorExtend.by;

import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import com.wqj.validator.validatorExtend.annotation.Matches;

/**
 * 验证二个属性是否值是否相等
 * 参考文献地址： http://clongjava.iteye.com/blog/1317649
 * @author Administrator
 *
 */
public class MatchesValidator implements ConstraintValidator<Matches,Object>{

	private String field;  
	private String verifyField;  

	public void initialize(Matches matches) {  
		this.field = matches.field();  
		this.verifyField = matches.verifyField();  
	}  

	@SuppressWarnings("deprecation")
	public boolean isValid(Object value, ConstraintValidatorContext context) {  
		try { 
			//获取要验证的属性值
			String fieldValue = BeanUtils.getProperty(value,field);  
			String verifyFieldValue = BeanUtils.getProperty(value,verifyField);  
			
			boolean valid = (fieldValue == null) && (verifyFieldValue == null);  
			if(valid){  
				return true;  
			}  

			boolean match = (fieldValue!=null) && fieldValue.equals(verifyFieldValue);  
			if(!match){  
				// 把验证出错的消息放在确认密码上显示密码不一致的消息。
				String messageTemplate = context.getDefaultConstraintMessageTemplate();  
				context.disableDefaultConstraintViolation();  
				context.buildConstraintViolationWithTemplate(messageTemplate)  
				.addNode(verifyField)  
				.addConstraintViolation();  
			}  
			return match;  
		} catch (IllegalAccessException e) {  
			e.printStackTrace();  
		} catch (InvocationTargetException e) {  
			e.printStackTrace();  
		} catch (NoSuchMethodException e) {  
			e.printStackTrace();  
		}
		return false;  
	}  
	
}
