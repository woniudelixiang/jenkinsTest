package com.wqj.validator.validatorExtend.annotation;

import javax.validation.Constraint;  
import javax.validation.Payload;

import com.wqj.validator.validatorExtend.by.MatchesValidator;

import java.lang.annotation.*; 

/**
 * 二个属性是否相等
 * @author Administrator
 *
 */
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = MatchesValidator.class)  
@Documented 
public @interface Matches {

	String message() default "两次输入不一致";  
    Class<?>[] groups() default {};  
    Class<? extends Payload>[] payload() default {};  
  
    String field();  
    String verifyField();  
    
}
