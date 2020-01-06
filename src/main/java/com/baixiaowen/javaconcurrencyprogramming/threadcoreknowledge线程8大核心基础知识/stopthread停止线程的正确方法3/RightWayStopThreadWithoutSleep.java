package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.stopthread停止线程的正确方法3;

/**
 * 描述：          run方法内没有sleep或wait方法是，停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2){
            if (num % 10000 == 0){
                System.out.println(num + "是10000的倍数");
            }
            num ++;
        }
        System.out.println("任务运行结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadWithoutSleep());
        t.start();
        Thread.sleep(2000);
        t.interrupt(); 
    }
    
}
