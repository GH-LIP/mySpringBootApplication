package com.test.thread.multiThread;

public class MultiThreadImplRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("新线程名："+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("主方法线程："+Thread.currentThread().getName());
        new Thread(new MultiThreadImplRunnable()).start();
    }
}
