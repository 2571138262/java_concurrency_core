package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：          俩个线程交替打印 0 - 100 的奇偶数，用wait和notify来提高效率
 */
public class WaitNotifyPrint0ddEveWait {
    // 1、一旦拿到锁，就打印
    // 2、打印完，唤醒其他线程，自己就休眠
    
    private static int count = 0;

    private static final Object lock = new Object();
    
    static class TruningRunner implements Runnable{
        @Override
        public void run() {
            while(count <= 100){
               synchronized (lock){
                   System.out.println(Thread.currentThread().getName() + ":" + count ++);
                   lock.notifyAll();
                   if (count <= 100){
                       try {
                           // 如果任务还没结束就让出这个锁
                           lock.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new TruningRunner(), "偶数").start();
        Thread.sleep(10);
        new Thread(new TruningRunner(), "奇数").start();
    }
    
}
