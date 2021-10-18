package com.test.thread.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 实现目标：6辆车，停3个车位
 * 信号量semaphore：信号量的许可数量，它不像 countDownLatch 和 cyclicBarrier 需要达到某种条件才可以执行，而是就这么多名额，你们随便，不影响我做其他事
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); // 创建信号量对象，设置许可数量（此处指车位数）

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();                // 从信号量中获取许可（抢占车位）
                    System.out.println(Thread.currentThread().getName() + "进入了车位");
                    Thread.sleep(new Random().nextInt(5));  // 随机休眠 0-5秒
                    System.out.println(Thread.currentThread().getName() + "------离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();                // 释放许可，返回给信号量
                }
            }, String.valueOf(i)).start();
        }
    }
}
