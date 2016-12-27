package com.wqj.common.annotation;

import java.lang.annotation.*;

/**
 * 绑定当前登录的用户
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月20日 下午5:36:57
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
	
  /**
   * 当前用户在request中的名字
   * @return
   */
    String value() default "user";

}
