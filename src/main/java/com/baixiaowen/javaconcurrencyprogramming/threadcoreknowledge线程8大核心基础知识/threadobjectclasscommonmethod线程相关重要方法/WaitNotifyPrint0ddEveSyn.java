package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：              俩个线程交替打印0 - 100的奇偶数
 *      synchronized关键字实现
 */
public class WaitNotifyPrint0ddEveSyn {
    private static int count = 0;
    private static final Object lock = new Object();

    // 新建2个线程
    // 1个只处理偶数，第二个只处理奇数
    // 用synchronized来通信

    public static void main(String[] args) {
        new Thread(() -> {
           while (count < 100){
               synchronized (lock){
                   if ((count & 1) == 0){
                       System.out.println(Thread.currentThread().getName() + ":" + count ++);
                   }
               }
           }
        }, "偶数").start();

        new Thread(() -> {
            while (count < 100){
                synchronized (lock){
                    if ((count & 1) == 1){
                        System.out.println(Thread.currentThread().getName() + ":" + count ++);
                    }
                }
            }
        }, "奇数").start();
    }
    
}
