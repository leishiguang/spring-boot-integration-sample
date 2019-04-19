package com.supermap.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * AOP 模板，在方法上切入
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Aspect
public class HelloMethodAspect {

    @Pointcut("execution(* com.supermap.service.impl.HelloServiceImpl.sayHello(..))")
    public void pointCut() {

    }

    @Before("pointCut() && args(name)")
    public void beforeParam(JoinPoint jp, String name) {
        Object[] args = jp.getArgs();
        if (args != null) {
            System.out.println("Args[0]...." + args[0]);
        }
        System.out.println("before ......" + name);
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after ......");
    }

    /**
     * 注意这儿的 around before 会优先于 before 执行
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before......");
        jp.proceed();
        System.out.println("around after......");
        return null;
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }
}
