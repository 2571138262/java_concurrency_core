package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.createthread创建线程1;

/**
 * 描述：      用Runnable方式创建线程
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
    
    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
