package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.sixstates线程的6种状态;

/**
 * 描述：              展示线程的NEW、RUNNING、Terminated状态
 *                      即使是正在运行，也是Runnable状态，而不是Running
 */
public class NewRunnableTerminated implements Runnable{


    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        // 打印出NEW的状态
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出Runnable状态，即使是正在运行也是Runnable而不是Running
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出Terminated
        System.out.println(thread.getState());
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
