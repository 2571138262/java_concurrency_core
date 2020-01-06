package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.startthread启动线程的正确方法2;

/**
 * 描述： 演示不能俩次调用start方法，否则会报错
 */
public class CanStartTwice {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
    
}
