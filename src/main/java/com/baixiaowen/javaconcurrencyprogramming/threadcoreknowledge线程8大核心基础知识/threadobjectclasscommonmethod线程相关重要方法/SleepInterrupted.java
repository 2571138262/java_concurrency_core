package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 描述：          每个1秒钟输出当前时间，被中断，观察
 *      Thread.sleep()
 *      TimeUnit.SECOND.sleep()
 */
public class SleepInterrupted implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        try {
            Thread.sleep(6500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.HOURS.sleep(3);        // 3小时
                TimeUnit.MINUTES.sleep(25);     // 25分钟
                TimeUnit.SECONDS.sleep(1);      // 1秒钟
            } catch (InterruptedException e) {
                System.out.println("我被中断了");
                e.printStackTrace();
            }
        }
    }
}
