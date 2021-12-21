package com.test.thread.threadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    // 第一步：创建资源类，定义属性和方法
    static class Resource {
        private int number = 1;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void incre () {
            try {
                // 先上锁
                lock.lock();
                // 1. 判断
                while (number != 0) {
                    condition.await();
                }
                // 2. 干活
                System.out.println(Thread.currentThread().getName() + " 加1：" + (number++) + " -> " + number);
                // 3. 通知其他线程
                condition.signalAll();
            } catch (Exception e) {

            } finally {
                // 最后必须手动释放所资源，lock必须手动解锁
                lock.unlock();
            }
        }

        public void decre () {
            try {
                // 先上锁
                lock.lock();
                // 1. 判断
                while (number != 1) {
                    condition.await();
                }
                // 2. 干活
                System.out.println(Thread.currentThread().getName() + " 减1：" + (number--) + " -> " + number);
                // 3. 通知其他线程
                condition.signalAll();
            } catch (Exception e) {

            } finally {
                // 最后必须手动释放锁资源，lock必须手动解锁
                lock.unlock();
            }
        }

        public static void main(String[] args) throws Exception {
            Resource resource = new Resource();

            // 第二步：创建多线程，调用方法
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    resource.decre();
                }
            }, "A");
            t1.start();

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    resource.incre();
                }
            }, "B");
            t2.start();
            t2.join(); // 排序,等待B的方法执行结束


            Thread t3 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    resource.decre();
                }
            }, "C");
            t3.start();


            Thread t4 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    resource.incre();
                }
            }, "D");
            t4.start();
        }

    }
}
