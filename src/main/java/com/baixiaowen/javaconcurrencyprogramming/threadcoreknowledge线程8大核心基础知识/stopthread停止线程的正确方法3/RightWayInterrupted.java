package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.stopthread停止线程的正确方法3;

/**
 * 描述：          注意Thread.interrupted()方法的目标对象是"当前线程"， 而不管本方法来自于哪个对象
 */
public class RightWayInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    
                }
            }
        });
        
        // 启动线程
        threadOne.start();
        // 设置中断标志
        threadOne.interrupt();
        // 获取中断状态
        System.out.println("isInterrupted:" + threadOne.isInterrupted()); // true
        // 获取中断状态并充值
        System.out.println("isInterrupted:" + threadOne.interrupted());   // false 
        // 获取中断标志并重置
        System.out.println("isInterrupted:" + Thread.interrupted());      // false
        // 获取中断标志
        System.out.println("isInterrupted:" + threadOne.isInterrupted()); // true 
        // 
        threadOne.join();
        System.out.println("Main thread is over");
    }
    
}
