package com.test.thread.threadSafe;

public class SynchronizedTest {

    private static Object obj = new Object();
    private static int number = 0;

    // 第一步：创建资源类，定义属性和方法
    static class Resource {

        public void incre () {
            synchronized (obj) {
                // 1. 判断
                while (number != 0) {
                    try {
                        obj.wait();  // 释放锁资源，线程进入等待状态，待线程被唤醒后，重新进入while判断（while可防止虚假唤醒）
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 2. 干活  被唤醒的线程，会执行以下代码
                number++;
                System.out.println(Thread.currentThread().getName() + "执行加一操作：" + number);
                // 3. 通知其他线程
                obj.notifyAll();
            }
        }

        public void decre () {
            synchronized (obj) {
                // 1. 判断
                while (number != 1) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 被唤醒的线程，会执行以下代码
                number--;
                System.out.println(Thread.currentThread().getName() + "执行减一操作：" + number);
                obj.notify();
            }
        }
    }

    // 第二步，创建线程
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.incre();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.decre();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.incre();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.decre();
            }
        }, "D").start();
    }

}
