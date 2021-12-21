package com.test.thread.threadSafe;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

public class ContainerNotSafeDemo {

    @Test
    public void mapNotSafe() {
        //Map<String,String> map=new HashMap<>();               // 线程非安全
        Map<String, String> map = new ConcurrentHashMap<>();    // 线程安全
        CountDownLatch c = new CountDownLatch(30);
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + map);
                c.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("map的长度："+ map.size());
    }

    @Test
    public void setNoSafe() {
        //Set<String> set=new HashSet<>();                      // 线程非安全
        Set<String> set = new CopyOnWriteArraySet<>();          // 线程安全
        CyclicBarrier c = new CyclicBarrier(7, () -> {
            System.out.println("set 长度："+ set.size());
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                set.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + "\t" + set);
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    @Test
    public void listNotSafe() throws InterruptedException {
        //List<String> list=new ArrayList<>();                  // 线程非安全
        List<String> list = new CopyOnWriteArrayList<>();       // 线程安全
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + list);
            }, String.valueOf(i)).start();
        }
        Thread.sleep(2000);
        System.out.println("list 长度："+ list.size());
    }
}
