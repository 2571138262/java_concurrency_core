package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法.test;

/**
 * 描述：              生产者消费者模式
 * 生产一个消费一个
 */
public class Test {
    public static void main(String[] args) {
        Cake cake = new Cake();
        new Thread(new Producer(cake)).start();
        new Thread(new Consumer(cake)).start();
    }
}

class Producer implements Runnable {

    private Cake cake;

    public Producer(Cake cake) {
        this.cake = cake;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            cake.make();
        }
    }
}

class Consumer implements Runnable {

    private Cake cake;

    public Consumer(Cake cake) {
        this.cake = cake;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            cake.eat();
        }
    }
}

class Cake {

    private int cakeNum = 0;

    // 生产蛋糕
    public void make() {
        synchronized (this) {
            if (cakeNum == 0)
                cakeNum ++;
            System.out.println("生产了 1 块蛋糕，现在有 " + cakeNum + " 块小蛋糕");
            this.notify();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 吃蛋糕
    public void eat() {
        synchronized (this) {
            if (cakeNum != 0)
                cakeNum --;
            System.out.println("吃掉了 1 块小蛋糕，当前有 " + cakeNum + " 块小蛋糕");
            this.notify();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

