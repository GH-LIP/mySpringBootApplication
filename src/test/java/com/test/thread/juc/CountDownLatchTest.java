package com.test.thread.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 实现目标：实现 6个同学都走出教室后，班长才锁门
 * countDownLatch 是 JUC 中一个线程安全的辅助类，作计数器用，每次减一，减到0才会执行await方法之后的代码
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        // 这种情况 班长可能在6名同学都出去之前锁门，不符合目标
//        for (int i = 1; i <= 6; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName()+"走出教室了");
//            }, String.valueOf(i)).start();
//        }
//        System.out.println(Thread.currentThread().getName()+ "班长锁门了");

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"走出教室了");
                countDownLatch.countDown();//每次操作完 计数器减一，直至计数器变为0，程序才会执行 countDownLatch.await(); 之后的代码
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();//countDownLatch 减为0之前，代码无法继续往下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ "班长锁门了");


    }
}
