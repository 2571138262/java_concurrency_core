package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;

/**
 * 描述：              初始化未完毕， 就this赋值
 */
public class MultiThreadErrorInitEscape {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(105);
        if (point != null){
            System.out.println(point);
        }
    }
    
}

class Point {
    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadErrorInitEscape.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}

class PointMaker extends Thread{
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
