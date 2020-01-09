package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：          演示join期间被中断的效果
 */
public class JoinInterrupt{

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread thread1 = new Thread(() -> {
            try {
                // 在子线程中让主线程中断
                // 因为子线程调用了 join方法， 也就是此时主线程正在等待子线程
                // 这个时候让主线程中断的话，主线程就会响应中断，停止等待
                mainThread.interrupt();
                Thread.sleep(5000);
                System.out.println("Thread1 finished");
            } catch (InterruptedException e) {
                System.out.println("子线程中断");
            }
        });
        
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "主线程被中断了");
            // 主线程响应中断以后， 再让子线程进行中断
            thread1.interrupt();
        }

        System.out.println("子线程已经运行完毕");
    }
    
}
