package com.baixiaowen.javaconcurrencyprogramming.jmmJava内存模型底层原理;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：             如果一个共享边浪自始至终只被各个线程赋值，而没有其他的操作，
 *                    那么久可以用volatile来代替synchronized或者代替原子变量，
 *                    因为赋值自身是有原子性的，而volatile又保证了可见性，所以就足以保证线程安全
 */
public class VolatileTest implements Runnable{

    private static int a;
    
    AtomicInteger atomicInteger = new AtomicInteger();
    
     
    
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            atomicInteger.incrementAndGet();   
            a = atomicInteger.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest vt = new VolatileTest();
        Thread thread1 = new Thread(vt);
        Thread thread2 = new Thread(vt);
        
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(a);
    }
}
