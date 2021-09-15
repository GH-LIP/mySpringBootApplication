package com.test.thread.threadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class SemaphoreTest {
    private static volatile int i = 0;                              // volatile 确保对多线程可见（可见性)

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);      // 设置计数器初始值：2
        Semaphore semaphore = new Semaphore(1);             // 设置信号量：1

        new Thread(() -> {
            try {
                semaphore.acquire();                                // 抢占信号，即占有该资源
                i++;
                semaphore.release();                                // 释放信号（资源）
                countDownLatch.countDown();                         // 计数器减一，减到0以后 方可执行后续代码
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-1").start();

        new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            semaphore.release();
            countDownLatch.countDown();
        }, "Thread-2").start();

        countDownLatch.await();         // 计数器变为0后，方可执行后续代码

        System.out.println(i);
    }
}
