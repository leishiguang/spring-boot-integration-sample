package com.supermap.aspect;

import com.supermap.annotation.Hello;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 注解切入
 *
 * @author leishiguang
 * @date 2019/03/15
 */
@Aspect
public class HelloAnnotationAspect {


    @Pointcut("@annotation(com.supermap.annotation.Hello)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void beforeParam(JoinPoint jp) {
        System.out.println("before...type:" + getType(jp) + ".....name:" + getName(jp));
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after ......");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }

    private String getName(JoinPoint joinPoint) {
        MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
        Method method = methodName.getMethod();
        return method.getAnnotation(Hello.class).name();
    }

    private Hello.Hello_TYPE getType(JoinPoint joinPoint) {
        MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
        Method method = methodName.getMethod();
        return method.getAnnotation(Hello.class).type();
    }
}
