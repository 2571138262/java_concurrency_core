package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：              3个线程，线程1和线程2首先被阻塞，线程3唤醒他们 notify、notifyAll
 *      start先执行不代表线程先启动
 */
public class WaitNotifyAll implements Runnable{

    private static final Object resourceA = new Object();
    
    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName() + "got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName() + "wait to start.");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "'s waiting to stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                resourceA.notifyAll();
//                resourceA.notify();
                System.out.println("ThreadC notifyAll");
            }
        });
        threadA.start();
        threadB.start();
//        Thread.sleep(20);

        threadC.start();
    }
}
