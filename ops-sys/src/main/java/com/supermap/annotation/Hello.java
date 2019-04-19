package com.supermap.annotation;

import java.lang.annotation.*;

/**
 * 测试 AOP 注解
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface Hello {

    enum HELLO_TYPE {
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
    HELLO_TYPE type() default HELLO_TYPE.ATHOR;
}
