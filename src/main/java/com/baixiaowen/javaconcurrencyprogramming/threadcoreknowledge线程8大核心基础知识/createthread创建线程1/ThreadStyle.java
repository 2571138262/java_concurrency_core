package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.createthread创建线程1;

/**
 * 描述：      用Thread方式实现线程
 */
public class ThreadStyle extends Thread{

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
    
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }
}
