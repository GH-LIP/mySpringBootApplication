package com.test.springAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component("logger") //非三层结构的对象，想要装配进spring中，可以使用 @Conponent
@Aspect // 表示切面类
@EnableAspectJAutoProxy //自动扫描该类，若配置文件中有自动扫描AOP的注解，则可去掉
public class Logger {

    @Pointcut("execution(* *..*.*(..))")  // 表示切入点方法，括号内为切入点表达式 写在最前面
    public void pt1 (){}
    /**
    @Before("pt1()")
    public void beforePrintLog () {
        System.out.println("前置通知...");
    }

    @AfterReturning("pt1()")
    public void afterReturningPrintLog () {
        System.out.println("后置通知...");
    }

    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog () {
        System.out.println("异常通知...");
    }

    @After("pt1()")
    public void afterPrintLog () {
        System.out.println("最终通知...");
    }


     * 环绕通知：有了环绕通知，可以不用其他通知，而通过代码位置来控制通知类型
     * 问题：当配置了环绕通知后，通知执行了，切入点方法没有执行，而通知方法执行了
     * 分析：通过对比动态代理的环绕通知代码，动态代理的环绕通知代码中有明确的切入点方法调用，而此处没有
     * 解决：Spring框架为我们提供了一个接口 ProceedingJoinPoint ,
     *      里面有一个方法 proceed(),此方法就相当于明确调用了切入点方法，
     *      1. 该方法会产生异常，但不能用 Exception 来捕捉此异常，而应该使用 Throwable 来捕捉，
     *          可在异常处理模块抛出新的异常：throw new RuntimeException(t);
     *      2. 该方法有返回值，需要接受返回值，将方法的返回值改为 Object 类型
     * Spring 的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中控制通知何时执行的方式。
     *      也就是说spring基于xml实现的aop可以通过配置实现，也可以通过代码实现。
     */
    @Around("pt1()")
    public Object aroundPrintLog (ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            System.out.println("环绕通知写在此处(切入点方法之前)表示前置通知...");
            Object args[] = pjp.getArgs();//获取切入点方法所需参数列表
            rtValue = pjp.proceed(args);//此方法表示明确调用了切入点方法
            System.out.println("环绕通知写在此处(切入点方法之后)表示后置通知...");
            return rtValue;
        } catch (Throwable t) {
            System.out.println("环绕通知写在此处(异常处理中)表示异常通知...");
            throw new RuntimeException(t);
        } finally {
            System.out.println("环绕通知写在此处(finally内部)表示最终通知...");
        }
    }
}
