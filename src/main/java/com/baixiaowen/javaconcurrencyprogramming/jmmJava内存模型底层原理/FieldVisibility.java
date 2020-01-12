package com.baixiaowen.javaconcurrencyprogramming.jmmJava内存模型底层原理;

/**
 * 描述：              演示可见性带来的问题
 */
public class FieldVisibility {

    volatile int a = 1;
    volatile int b = 2;

    private void change() {
        a = 3;
        b = a;
    }

    private void print() {
        System.out.println("b = " + b + "; a = " + a);
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (i < 10) {// 
            i++;
            FieldVisibility test = new FieldVisibility();
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            });

            thread1.start();
            thread2.start();


        }
        /**
         * 分析这四种情况：
         *  a = 3, b = 2
         *  a = 1, b = 2
         *  a = 3, b = 3
         *
         *  a = 1, b = 3
         */
    }

}
