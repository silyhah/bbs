package com.sily.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalInterceptor {

    /**
     * 是否需要登录校验
     * @return
     */
    boolean checkLogin() default false;

    /**
     * 是否需要校验参数
     * @return
     */
    boolean checkParam() default false;
}
