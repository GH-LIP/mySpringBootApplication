package com.test.designPattern.singleton;

/**
 * 单例模式-饿汉式
 *      定义： 类加载时即创建对象
 *      优点： 实现简单、没有线程同步安全问题
 *      缺点： 可能对象不需要使用，创建对象反而浪费内存
 *      使用： 如果对象不大，且创建不复杂时可选择
 */
public class SingletonHungry {
    private static SingletonHungry singletonHungry = new SingletonHungry();
    public static SingletonHungry getInstance() {
        return singletonHungry;
    }
}
