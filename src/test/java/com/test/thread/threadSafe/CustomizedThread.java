package com.test.thread.threadSafe;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 目标： A线程 打印5次，然后 B线程 打印10次， 最后， C线程 打印15次。  完成三轮 A5 B10 C15 打印
public class CustomizedThread {

    // 第一步：创建资源，定义属性和方法
    static class ShareResource {
        private Lock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();
        private int flag = 1;

        public void print5(int loopTime) {
            // 加锁
            lock.lock();
            try {
                // 1. 判断
                while (flag != 1) {
                    c1.await();
                }
                // 2. 干活
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " :: " + i + "次 :: " + loopTime + "轮");
                }
                // 3. 通知其他线程：B
                flag = 2;
                c2.signal();
            } catch (InterruptedException e) {

            } finally {
                // 释放锁
                lock.unlock();
            }
        }

        public void print10(int loopTime) {
            // 加锁
            lock.lock();
            try {
                // 1. 判断
                while (flag != 2) {
                    c2.await();
                }
                // 2. 干活
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " :: " + i + "次 :: " + loopTime + "轮");
                }
                // 3. 通知其他线程：C
                flag = 3;
                c3.signal();
            } catch (InterruptedException e) {

            } finally {
                // 释放锁
                lock.unlock();
            }
        }

        public void print15(int loopTime) {
            // 加锁
            lock.lock();
            try {
                // 1. 判断
                while (flag != 3) {
                    c3.await();
                }
                // 2. 干活
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName() + " :: " + i + "次 :: " + loopTime + "轮");
                }
                // 3. 通知其他特定线程
                flag = 1;
                c1.signal();
            } catch (InterruptedException e) {

            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }


    // 第二步：创建多线程，调用方法
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                shareResource.print5(i);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                shareResource.print10(i);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                shareResource.print15(i);
            }
        }, "C").start();
    }
}
