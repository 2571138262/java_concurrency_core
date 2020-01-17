package com.baixiaowen.javaconcurrencyprogramming.jmmJava内存模型底层原理;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：              不适用于volatile的场景
 */
public class NoVolatile implements Runnable{

    volatile int a;
    
    AtomicInteger realA = new AtomicInteger();
    
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
          a ++;  
          realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoVolatile nv = new NoVolatile();
        Thread thread1 = new Thread(nv);
        Thread thread2 = new Thread(nv);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(nv.a);
        System.out.println("realA: " + nv.realA.get());
        
    }
}
