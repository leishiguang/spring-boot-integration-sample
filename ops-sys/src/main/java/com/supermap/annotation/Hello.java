package com.supermap.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface Hello {

    enum Hello_TYPE {ADD, ATHOR}

    /**
     * 内容
     */
    String name();

    /**
     * 类型
     */
    Hello_TYPE type() default Hello_TYPE.ATHOR;
}
