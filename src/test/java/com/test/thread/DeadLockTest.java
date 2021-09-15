package com.test.thread;

// 快速实现 死锁代码， 并利用  jps(类似于linux中的ps -ef命令)、jstack(jvm自带的堆栈跟踪工具) 验证是否死锁

/**
 * 如何实现jps验证是否为死锁？
 *
 * 1. 首先运行死锁的代码，
 * 2. 进入jdk的安装目录下的bin目录
 * 3. jps -l 查看,结果如下
 *          $ jps -l
 *          10292 org.jetbrains.idea.maven.server.RemoteMavenServer36
 *          13236 sun.tools.jps.Jps
 *          4500 org.jetbrains.jps.cmdline.Launcher
 *          7428
 *          3544 com.test.thread.DeadLockTest
 * 4. 可以看到最后一行有DeadLockTest这个类，这就是我们写的死锁测试代码，找到前面的编号3544
 * 5. 利用jvm自带的堆栈跟踪工具，运行命令 jstack 3544 ，查看死锁信息：....Found 1 deadlock.
 * 这就是验证死锁的过程，整理到语雀中
 */
public class DeadLockTest {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();

        new Thread(()->{
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "占有a锁,并试图获取b锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取b锁");
                }
            }
        }, "小李").start();

        new Thread(()->{
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "占有b锁,并试图获取a锁");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取a锁");
                }
            }
        }, "小全").start();
    }
}
