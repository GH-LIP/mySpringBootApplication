package com.test.thread.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 实现目标：集齐七颗龙珠召唤神龙
 * cyclicBarrier：循环障碍，当数量达到指定的值才会执行 CyclicBarrier 构造函数内的代码
 *      cyclicBarrier  循环障碍，执行加一操作，达到指定值才会执行 CyclicBarrier 构造函数内的代码，在此之前会处于阻塞状态；
 *      countDownLatch 计数器锁，执行减一操作，变为0才会执行 countDownLatch.await(); 之后的代码，在此之前会处于阻塞状态。
 */
public class CyclicBarrierTest {
    private static final int NUMBER = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, ()->{
            System.out.println("集齐七颗龙珠召唤神龙");// 只有
        });

        // 收集七颗龙珠过程
        for (int i = 1; i <= 7; i++) {  //超过或少于循环障碍的设定值，线程都会处于阻塞状态
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "星龙珠到手");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }



    }
}
