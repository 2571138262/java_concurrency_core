package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.startthread启动线程的正确方法2;

/**
 * 描述：      对比start() 和 run() 俩种启动线程的方式
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        new Thread(runnable).start();
        runnable.run();
    }
    
}
