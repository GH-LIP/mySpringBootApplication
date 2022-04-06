package com.test.thread.threadPool;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        //
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 30, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        // 单核心的线程池，使用无界队列 LinkedBlockingQueue
        Executors.newSingleThreadExecutor();

        // 固定核心数的线程池，使用无界队列 LinkedBlockingQueue，默认拒绝策略
        Executors.newFixedThreadPool(10);

        // 按照计划规定执行的线程池，默认拒绝策略
        Executors.newScheduledThreadPool(10);

        // 自动增长的线程池
        Executors.newCachedThreadPool();

        /**
         * 具有抢占式操作的线程池,newWorkStealingPool，JDK1.8 新增，用的是 ForkJoinPool 类，
         * 它是一个并行的线程池，参数中传入的是一个线程并发的数量, 不会保证任务的顺序执行。
         */
        Executors.newWorkStealingPool(3);

    }
}
