package com.test.thread.threadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockTest {
    private static volatile int i = 0;  // volatile 保证变量在多线程间的可见性

    public static void main(String[] args) throws InterruptedException {
        // 创建计数器锁对象，设置初始值（初始值一般和线程数量相同）
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try{
                lock.lock();
                for (int j = 0; j < 100; j++) {
                    i++;
                }
            }finally{
                lock.unlock();
                countDownLatch.countDown();     //计数器-1
            }
        },"Thread-1");

        Thread thread2 = new Thread(() -> {
            try{
                lock.lock();
                for (int j = 0; j < 100; j++) {
                    i++;
                }
            }finally{
                lock.unlock();
                countDownLatch.countDown();
            }
        },"Thread-2");

        thread1.start();
        thread2.start();
        countDownLatch.await();

        System.out.println(i);
    }
}
