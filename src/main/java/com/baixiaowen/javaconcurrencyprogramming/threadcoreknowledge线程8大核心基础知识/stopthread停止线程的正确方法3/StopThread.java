package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.stopthread停止线程的正确方法3;

/**
 * 描述：              错误的停止方法：用stop()来停止线程，会导致线程运行一半突然停止， 没有办法完成一个基本单位的操作
 *          模拟场景：一个连队作为一个基本单位，  会造成脏数据(有的连队多领取少领取装备)
 */
public class StopThread implements Runnable{

    @Override
    public void run() {
        // 模拟指挥军队： 一共有5个联队，每个联队10人，以联队为单位，发放武器弹药，叫到号的士兵去领取
        for (int i = 0; i < 5; i++) {
            System.out.println("联队" + i + "开始领取武器");
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("联队" + i + "已领取完毕");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
