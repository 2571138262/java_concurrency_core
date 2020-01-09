package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：              通过讲解join原理，分析出join的代替写法
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });
        
        thread1.start();
        System.out.println("开始等待子线程运行完毕");
//        thread1.join();
        // 等价代码 == thread1.join();
        // 每个Thread类在执行了run方法之后，都会自动执行notify方法来唤醒其他等待的线程
        // 所以这里虽然没有显示的notify 唤醒主线程， 但是会默认被唤醒 
        synchronized (thread1){
            thread1.wait();
        }
        System.out.println("所有线程执行完毕");
    }
    
}
