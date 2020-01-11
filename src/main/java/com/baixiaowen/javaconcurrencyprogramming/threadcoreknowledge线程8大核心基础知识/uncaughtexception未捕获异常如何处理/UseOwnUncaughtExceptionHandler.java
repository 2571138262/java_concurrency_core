package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.uncaughtexception未捕获异常如何处理;

/**
 * 描述：          当前子线程使用刚才自己写的UncaughtExceptionHandler， 不使用默认的异常捕获器
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        
        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread - 1").start();
        Thread.sleep(300);

        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread - 2").start();
        Thread.sleep(300);

        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread - 3").start();
        Thread.sleep(300);

        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread - 4").start();
        Thread.sleep(300);
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
