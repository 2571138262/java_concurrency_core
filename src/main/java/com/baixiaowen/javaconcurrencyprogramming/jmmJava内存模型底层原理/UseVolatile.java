package com.baixiaowen.javaconcurrencyprogramming.jmmJava内存模型底层原理;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：              volatile适用的情况1
 */
public class UseVolatile implements Runnable{

    volatile boolean done;

    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatile nv = new UseVolatile();
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
