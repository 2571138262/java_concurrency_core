package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.createthread创建线程1;

/**
 * 描述：              同时使用Runnable和Thread会怎么样
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
    
}
