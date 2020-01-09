package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：          证明wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOwnMonitor {
    
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("ThreadA got resourceA lock.");
                System.out.println("ThreadA tries to resourceB lock.");
                synchronized (resourceB) {
                    System.out.println("ThreadA got resourceB lock");
                    try {
                        resourceA.wait();
                        System.out.println("ThreadA releases resourceA lock");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA){
                System.out.println("ThreadB got resourceA lock.");
                System.out.println("ThreadB tries to resourceB lock.");
                synchronized (resourceB){
                    System.out.println("ThreadB got resourceB lock.");   
                }
            }
        });
        
        
        threadB.start();
        threadA.start();
    }
    
}
