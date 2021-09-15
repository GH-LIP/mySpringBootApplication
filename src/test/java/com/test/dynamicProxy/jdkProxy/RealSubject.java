package com.test.dynamicProxy.jdkProxy;

/**
 * @auther: LIP
 * @create: 2021-01-23 22:10
 * @desc:
 */
public class RealSubject implements MySubject {

    @Override
    public void doSomething() {
        System.out.println("周而复始，如梦方醒！");
    }

    @Override
    public void doNothing() {
        System.out.println("陌路");
    }
}
