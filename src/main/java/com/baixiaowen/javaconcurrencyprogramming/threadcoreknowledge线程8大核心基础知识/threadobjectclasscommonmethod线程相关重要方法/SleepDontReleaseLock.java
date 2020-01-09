package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：                          演示sleep不释放lock (lock需要手动释放)
 */
public class SleepDontReleaseLock implements Runnable{

    private static final Lock lock = new ReentrantLock();
    
    @Override
    public void run() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程" + Thread.currentThread().getName() + "已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock r = new SleepDontReleaseLock();
        new Thread(r).start();
        new Thread(r).start();
    }
}
