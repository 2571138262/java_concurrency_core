package com.baixiaowen.javaconcurrencyprogramming.jmmJava内存模型底层原理;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：              volatile 不 适用的情况2
 */
public class NoVolatile2 implements Runnable{

    volatile boolean done;

    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            flipDone();
            realA.incrementAndGet();
        }
    }

    /**
     * 状态反转
     */
    private void flipDone() {
        done = !done;
    }

    public static void main(String[] args) throws InterruptedException {
        NoVolatile2 nv = new NoVolatile2();
        Thread thread1 = new Thread(nv);
        Thread thread2 = new Thread(nv);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(nv.done);
        System.out.println("realA: " + nv.realA.get());

    }
}
