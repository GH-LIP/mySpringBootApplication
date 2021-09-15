package com.test.thread.threadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 第一步：创建资源类，定义属性和方法
class Ticket {

    private int number = 30;
//    private Lock lock = new ReentrantLock();           // 非公平锁：效率高，分布不均
    private Lock lock = new ReentrantLock( true);   // 公平锁：效率低，线程间公平分配资源
    private Condition c = lock.newCondition();

    public void saleTicket() {
        lock.lock();
        try {
            // 1. 判断
            while(number <= 0) {
                c.await();
            }
            // 2. 干活
            System.out.println(Thread.currentThread().getName() + " 拿到" + number + "张票 :: " + "卖出一张，剩余：" + (--number));
            // 3. 通知(唤醒)其他线程
            c.signal();
        } catch(Exception e) {

        } finally {
            lock.unlock();
        }

    }

}

public class SaleTicketLockTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.saleTicket();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.saleTicket();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.saleTicket();
            }
        },"C").start();
    }
}
