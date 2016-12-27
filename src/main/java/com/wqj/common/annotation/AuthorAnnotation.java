package com.wqj.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wqj.common.enums.AuthorName;

/**
 * 自定义注解，用于注明作者名称
 *
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016-4-6 下午5:23:31
 */

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorAnnotation {
	AuthorName authorName() default AuthorName.missing;
	
}
