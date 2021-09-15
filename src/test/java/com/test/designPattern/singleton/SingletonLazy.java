package com.test.designPattern.singleton;

/**
 * 单例模式-懒汉式
 * 定义： 需要的时候才创建对象，对象唯一即单例， 懒汉指懒加载、延迟加载 （常用，有线程安全问题）
 * 注意：
 * 使用：若对象很大，或创建复杂，则直接使用懒汉式
 */
public class SingletonLazy {

    private static SingletonLazy singletonLazy;
    /**
     * 第一种：
     *      1、私有化构造函数
     *      2、提供对外接口 getInstance 判断对象是否为空 空即创建
     *  问题： 存在线程安全问题，多线程情况下 线程 A、B 同时进入判断  if (singletonLazy == null) 就会创建多个线程
     *  解决： 加锁: 在方法体上
     *  volitate
     */
    private SingletonLazy () {}
    public static SingletonLazy getInstance () {
        if (singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }

    /**
     * 第二种：
     *      1、私有化构造函数
     *      2、方法上加锁 synchronized
     *  问题： 高并发环境性能开销大
     *  解决： 缩小锁粒度
     *
     */
    public static synchronized SingletonLazy getInstance_improve1 () {
        if (singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }

    /**
     * 第三种：
     *      1、私有化构造函数
     *      2、语句加锁 synchronized
     *  问题：仍存在线程安全问题： A、B 都进入了非空判断， A先得到锁，等A创建好对象释放锁后，B又新建路对象
     *  解决： 双重判断 DCL （double-checked-）
     *
     */
    public static synchronized SingletonLazy getInstance_improve2 () {
        if (singletonLazy == null) {
            synchronized (SingletonLazy.class) {
                singletonLazy = new SingletonLazy();
            }
        }
        return singletonLazy;
    }

    /**
     * 第四种：
     *      1、私有化构造函数
     *      2、语句加锁 获取锁前后都做非空判断
     *  问题：仍存在线程安全问题： A、B 都进入了非空判断， A先得到锁，等A创建好对象释放锁后，B又新建路对象
     *  解决： 双重检查锁定 DCL （double-checked-locking） ，能在多线程下保持高性能
     *
     */
    public static SingletonLazy getInstance_improve3 () {
        if (singletonLazy == null) {
            synchronized (SingletonLazy.class) {
                if (singletonLazy == null) {
                    singletonLazy = new SingletonLazy();
                }
            }
        }
        return singletonLazy;
    }


    /**
     * 第五种：
     *      1、私有化构造函数
     *      2、禁止对象指令重排
     *  问题：  singletonLazy = new SingletonLazy();  不是原子性操作
     *  解决：  杜绝指令重排  Java 关键字 volatile
     *
     *** 拓展：
     *  创建对象步骤：
     *      1、给对象分配空间
     *      2、在空间内创建对象
     *      3、将对象赋值给引用对象
     *   指令重排：线程执行顺序可以为： 1》2》3 也可以为 1》3》2，前者为顺序执行，没有问题，后者即为指令重排
     *      执行完3后，程序会把引用对象的值写入主内存，其他线程读取到该值会报错，因为2可能还没被执行，对象不存在
     */
    public static SingletonLazy getInstance_improve4 () {
        if (singletonLazy == null) {
            synchronized (SingletonLazy.class) {
                if (singletonLazy == null) {
                    singletonLazy = new SingletonLazy();
                }
            }
        }
        return singletonLazy;
    }


    /**
     * 第六种：
     *      既能在高并发情况下保证性能，也可以禁止指令重排，解决线程安全问题
     */
    private static volatile SingletonLazy singletonLazy_volatile;
    public static SingletonLazy getInstance_improve5 () {
        if (singletonLazy_volatile == null) {
            synchronized (SingletonLazy.class) {
                if (singletonLazy_volatile == null) {
                    singletonLazy_volatile = new SingletonLazy();
                }
            }
        }
        return singletonLazy_volatile;
    }



}
