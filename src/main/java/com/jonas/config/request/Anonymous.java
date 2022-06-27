package com.jonas.config.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenjy
 * @createTime 2022/6/27 17:56
 * @description 匿名注解，使用该注解表示可以匿名访问
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}
