package com.baixiaowen.javaconcurrencyprogramming.jmmJava内存模型底层原理;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：          演示重排序的现象
 * 重排序不是每次都能出现
 * 知道达到某个条件才停止，可以用来测试小概率事件
 */
public class OutOfOrderExecution {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i ++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            /**
             * 这里CountDownLatch的作用是保证俩个线程可以近乎同时执行对 a、b、x、y的赋值操作
             * 
             *      调用了await()的线程会等待， 有其他线程调用了n次countDown(), 这里 n = 1
             */
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });

            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });

            /**
             * 这四行代码的执行顺序决定了最终 x 和 y 的结果， 一共有 3 种情况
             * 1、a = 1; x = b(0); b = 1; y = a(1), 最终结果试试 x = 0, y = 1
             * 2、b = 1; y = a(0); a = 1; x = b(1), 最终结果试试 x = 1, y = 0
             * 3、b = 1; a = 1; x = b(1); y = a(1), 最终结果试试 x = 1, y = 1
             * 
             * 
             */

            one.start();
            two.start();
            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i + "次 （" + x + ", " + y + ").";
            if (x == 0 && y == 0){
                System.out.println(result);
                break;
            }
            System.out.println(result);

            /**
             * 这个案例， 传统意义上讲是不会出现 x = 0, y = 0 的情况的，但是代码上确实是出现了
             * 
             * 会出现 x = 0, y = 0？ 那是因为重排序发生了， 4行代码的执行顺序的其中一种可能
             *      此时执行顺序是：
             *          y = a;
             *          a = 1;
             *          x = b;
             *          b = 1;
             */
        }
    }

}
