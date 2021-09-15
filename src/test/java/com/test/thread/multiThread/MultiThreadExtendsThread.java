package com.test.thread.multiThread;

/**
 * @auther: LIP
 * @create: 2021-01-24 11:14
 * @desc:
 */
public class MultiThreadExtendsThread extends Thread {
    
    public void print () {
        System.out.println("当前线程："+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("此处线程名："+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("当前线程名："+ Thread.currentThread().getName());
        new MultiThreadExtendsThread().start();

        // Lambda 表达式 ,也叫函数式接口
        new Thread (() -> {
            System.out.println("新建线程名称："+Thread.currentThread().getName());
        }).start();
    }
}
