package com.test.thread.threadSafe;

public class WaitAndNotify {
    //轮询标志位
    private static boolean stop = false;
    //监视器对应的对象
    private static Object monitor = new Object();
    //等待线程
    static class WaitThread implements Runnable{
        @Override
        public void run() {
            synchronized (monitor){
                //循环检测标志位是否变更
                while(!stop){               // 条件if会导致虚假唤醒，应使用 while
                    try {
                        //标志位未变更，进行等待
                        monitor.wait();     // wait() 在哪里调用在哪里唤醒，唤醒后不会再次进入if判断，所以上面的 while 如果换成 if，则会导致虚假唤醒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒后获取到对象的监视器之后执行的代码
                System.out.println("Thread "+Thread.currentThread().getName()+" is awakened at first time");
                stop = false;
            }
            //休眠1秒之后，线程角色转换为唤醒线程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //与上述代码相反的逻辑
            synchronized(monitor){
                while(stop){
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                monitor.notify();
                stop = true;
                System.out.println("Thread "+ Thread.currentThread().getName()+" notifies the waitted thread at first time");
            }
        }
    }
    //通知线程
    static class NotifyThread implements Runnable{
        @Override
        public void run() {
            synchronized (monitor){
                while(stop){
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stop = true;
                monitor.notify();
                System.out.println("Thread "+ Thread.currentThread().getName()+" notifies the waitted thread at first time");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (monitor){
                while(!stop){
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread "+Thread.currentThread().getName()+" is awakened at first time");
            }
        }
    }

    public static void main(String[] args){
        Thread waitThread = new Thread(new WaitThread());
        waitThread.setName("waitThread");
        waitThread.start();
        // 下面一行代码即可表示上面三行代码
        new Thread(new NotifyThread(), "notifyThread").start();
    }
}
