package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;

/**
 * 描述：          第二种线程安全问题， 演示死锁
 */
public class MultiThreadErrorDeathLock implements Runnable{

    int flag = 1;
    
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MultiThreadErrorDeathLock r1 = new MultiThreadErrorDeathLock();
        MultiThreadErrorDeathLock r2 = new MultiThreadErrorDeathLock();
        r1.flag = 1;
        r2.flag = 0;
        
        new Thread(r1).start();
        new Thread(r2).start();
    }
    
    @Override
    public void run() {
        System.out.println("falg = " + flag);
        if (flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }

        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("0");
                }
            }
        }
    }
}
