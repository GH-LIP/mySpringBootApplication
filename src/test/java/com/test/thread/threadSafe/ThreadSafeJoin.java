package com.test.thread.threadSafe;

public class ThreadSafeJoin implements Runnable{
    //静态变量，所有对象共享
    private static int count = 0;
    @Override
    public void run() {
//        synchronized (ThreadSafeJoin.class) {
            for (int i = 0; i < 100; i++) {
                count++;
            }
//        }
    }
    public void count(){
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadSafeJoin threadSafeJoin = new ThreadSafeJoin();
        Thread thread1 = new Thread(threadSafeJoin);
        Thread thread2 = new Thread(threadSafeJoin);
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        System.out.println(count);
    }
}
