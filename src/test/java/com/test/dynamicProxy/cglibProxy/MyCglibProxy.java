package com.test.dynamicProxy.cglibProxy;

import com.test.dynamicProxy.jdkProxy.RealSubject;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // TODO 自动生成的方法存根
        System.out.println("before invokeSuper ".concat(method.getName()));
        methodProxy.invokeSuper(o, objects);
        //method.invoke(caller, args);
        System.out.println("after invokSuper ".concat(method.getName()));
        return null;
    }

    @Test
    public void ml () {
        //创建类加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //指定父类，也就是目标类
        enhancer.setSuperclass(RealSubject.class);
        //指定回调方法，当调用代理对象的某个方法时，此回调方法将被调用
        enhancer.setCallback(new MyCglibProxy());
        //利用类加强器en创建代理对象
        RealSubject ns = (RealSubject) enhancer.create();
        ns.doSomething();
        ns.doNothing();
    }
}
