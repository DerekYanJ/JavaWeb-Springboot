package com.example.demo.springboot.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logs {
    /**
     * 接口说明
     */
    String desc() default "";

    boolean isPrint() default false;
}
