package com.test.designPattern.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auther: LIP
 * @create: 2021-01-23 22:02
 * @desc:
 */
public class DynamicProxy implements InvocationHandler {
    private Object tar;
    /**
     * 绑定该类实现的所有接口，取得代理类（此步骤可和invoke方法合并）
     * 参数1：ClassLoader loader  指定当前目标对象使用的类加载器
     * 参数2：Class<?>[] interfaces  指定目标对象实现的接口的类型,使用泛型方式确认类型
     * 参数2：InvocationHandler  指定动态处理器，执行目标对象的所有方法都会触发事件处理器的invoke方法
     */
    public Object bind (Object tar) {
        this.tar = tar;
        return Proxy.newProxyInstance (tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),
                this);// this指当前对象
    }

    @Override
    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        // 此处进行AOP 编程
        // 在调用具体函数方法前，执行功能处理
        System.out.println("之前，代理替你做的事！");
        result = method.invoke(tar,args);
        // 在调用具体函数方法后，执行功能处理
        System.out.println("之后，代理替你做的事！");
        return result;
    }
}
