package com.baixiaowen.javaconcurrencyprogramming.deadlock死锁;

/**
 * 描述：          必定发生死锁的情况
 */
public class MustDeadLock implements Runnable{

    int flag = 1;

    static Object o1 = new Object();

    static Object o2 = new Object();

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();

        MustDeadLock r2 = new MustDeadLock();

        r1.flag = 1;
        r2.flag = 0;

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);

        /**
         * 当类的对象flag = 1 时(T1)，先锁定o1，睡眠500毫秒，然后锁定o2；
         *
         *
         * T1睡眠结束后需要锁定o2才能继续执行，而此时o2已被T2锁定
         */
        if (flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }

        /**
         * 而T1在睡眠的时候另一个flag = 0的对象(T2)线程启动，先锁定o2，睡眠500毫秒，等待T1释放o1；
         *
         *
         * T2睡眠结束后需要o1才能继续执行，而此时o1已被T1锁定
         *
         * T1 和 T2相互等待，都需要对象锁定的资源才能继续执行，从而死锁
         */
        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }
}