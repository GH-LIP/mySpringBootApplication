package com.test.dynamicProxy.jdkProxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auther: LIP
 * @create: 2021-01-23 22:22
 * @desc:
 */

public class TestProxy {
    @Test
    public void test() {
        DynamicProxy dynamicProxy = new DynamicProxy();
        // 绑定该类实现的所有接口
//        MySubject proxy = (MySubject) dynamicProxy.bind(new RealSubject());
//        proxy.doSomething();

        /** 另一种写法：使用匿名内部类重写invoke方法，实现增强功能 */
        MySubject proxyInstance = (MySubject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result;
                        // 此处进行AOP 编程
                        // 在调用具体函数方法前，执行功能处理
                        System.out.println("前生！");
                        result = method.invoke(new RealSubject(), args);
                        // 在调用具体函数方法后，执行功能处理
                        System.out.println("後世");
                        return result;
                    }
                });
        proxyInstance.doSomething();

    }
}
