package com.supermap.annotation;

import java.lang.annotation.*;

/**
 * 测试 AOP 注解
 *
 * @author leishiguang
 * @date 2019/03/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface Hello {

    enum Hello_TYPE {
        /**
         * 测试类型1
         */
        ADD,
        /**
         * 测试类型2
         */
        ATHOR
    }

    /**
     * 内容
     */
    String name();

    /**
     * 类型
     */
    Hello_TYPE type() default Hello_TYPE.ATHOR;
}
