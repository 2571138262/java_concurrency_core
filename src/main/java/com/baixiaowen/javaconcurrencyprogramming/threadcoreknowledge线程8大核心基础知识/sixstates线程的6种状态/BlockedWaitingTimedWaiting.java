package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.sixstates线程的6种状态;

/**
 * 描述：              展示Blocked、Waiting、TimedWaiting三种状态
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(runnable);
        thread2.start();
        // 打印出TIMED_WAITING   因为正在执行 Thread.sleep(1000);
        System.out.println(thread1.getState());
        // 打印出BLOCKED，因为 thread2想拿到syn()方法的锁却拿不到
        System.out.println(thread2.getState());

        try {
            Thread.sleep(501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印出WAITING状态，因为 thread1 执行了 Object的wait()方法，还没有别唤醒
        System.out.println(thread1.getState());
    }
    
    @Override
    public void run() {
        syn();
    }
    
    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
