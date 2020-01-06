package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.createthread创建线程1.wrongways错误的创建线程方式;

/**
 * 描述：              Lambda表达式
 */
public class Lambda {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
    
}
