package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.createthread创建线程1.wrongways错误的创建线程方式;

/**
 * 描述：              匿名内部类实现线程
 */
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
    
}
