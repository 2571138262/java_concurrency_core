package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：              第一种： 运行结果错误
 * 演示计数不准确(减少), 找出具体出错的位置
 */
public class MultiThreadsError implements Runnable {

    static MultiThreadsError instance = new MultiThreadsError();

    int index = 0;

    static AtomicInteger realIndex = new AtomicInteger(); // 实际上的累加值
    static AtomicInteger wrongCount = new AtomicInteger();// 错误的累加次数
    
    // 同步工具类
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    final boolean[] marked = new boolean[10000000];

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是：" + instance.index);
        System.out.println("真正运行次数：" + realIndex.get());
        System.out.println("错误次数：" + wrongCount.get());
    }

    @Override
    public void run() {
//        while (index < 10000){
//            index ++;
//        }
        
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生错误 -- " + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
