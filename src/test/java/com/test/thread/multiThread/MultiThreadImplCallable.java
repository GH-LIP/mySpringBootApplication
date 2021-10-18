package com.test.thread.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MultiThreadImplCallable implements Callable<String> {
    @Override
    public String call() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "这就是线程执行任务的返回结果";
    }

    public static void main(String[] args) {
        MultiThreadImplCallable callableThread = new MultiThreadImplCallable();
        FutureTask<String> futureTask = new FutureTask(callableThread);
        new Thread(futureTask).start();

        String result = null;
        try {
            while(!futureTask.isDone()) {
                System.out.println("waiting...");
            }
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("callable执行结果："+result);
    }
}
