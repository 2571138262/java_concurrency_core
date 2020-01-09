package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：              展示wait和notify的基本用法
 *      1、研究diamante执行顺序
 *      2、证明wait释放锁
 */
public class Wait {
    
    public static Object object = new Object();
    
    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行了");
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "执行了wait方法");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "被唤醒重新获取到了锁");
            }
        }
    }
    
    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行了");
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        
        thread1.start();
        Thread.sleep(10);
        thread2.start();
    }
    
}
