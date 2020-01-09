package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：          先join再mainThread.getState()
 *      通过debugger 看线程join前后状态对比
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                // 打印主线程的状态：     WAITING
                System.out.println(mainThread.getState());
                System.out.println("Thread-0运行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        thread.start();
        System.out.println("等待子线程执行完毕");
        thread.join();
        System.out.println("子线程执行完毕");
    }
    
}
