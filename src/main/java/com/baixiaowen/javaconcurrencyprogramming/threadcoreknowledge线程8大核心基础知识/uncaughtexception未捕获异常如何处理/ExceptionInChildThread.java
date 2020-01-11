package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.uncaughtexception未捕获异常如何处理;

/**
 * 描述：          单线程，抛出，处理， 有异常堆栈
 *                 多线程， 子线程发生异常， 会有什么不同
 */
public class ExceptionInChildThread implements Runnable{

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        
    }
    
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
