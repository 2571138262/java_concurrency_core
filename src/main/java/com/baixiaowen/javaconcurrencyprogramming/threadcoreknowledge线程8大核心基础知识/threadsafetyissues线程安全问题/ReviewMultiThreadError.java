package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：     写出并发安全案例
 *                  出现错误的位置就是num++的地方
 *            并且将并发出错的地方显示的指出
 */
public class ReviewMultiThreadError implements Runnable{

    static ReviewMultiThreadError r = new ReviewMultiThreadError();
    
    static int num = 0;
    // 设置标记位
    boolean[] index = new boolean[200001];
    
    static CyclicBarrier c1 = new CyclicBarrier(2);
    static CyclicBarrier c2 = new CyclicBarrier(2);
    
    static AtomicInteger realCount = new AtomicInteger();
    static AtomicInteger wrong = new AtomicInteger();
    
    @Override
    public void run() {
        index[0] = true;
        for (int i = 0; i < 100000; i++) {
            try {
                c2.reset();
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            num ++;
            try {
                c1.reset();
                c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realCount.incrementAndGet();
            synchronized (r){
                if (index[num] && index[num - 1]){ // 当两个线程同时抢占一个位置的时候， 前一个位置则说明没有人抢占(false)
                    wrong.incrementAndGet();
                    System.out.println("错误地方为：" + num);
                }
                index[num] = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("最终累加的值为： " + num);
        System.out.println("实际累加值： " + realCount);
        System.out.println("错误累加次数： " + wrong);
    }
}
