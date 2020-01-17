package com.baixiaowen.javaconcurrencyprogramming.jmmJava内存模型底层原理;

/**
 * 描述：              演示可见性带来的问题
 */
public class FieldVisibility {

    /**
     * 近朱者赤： 给b加了volatile，不仅b被影响，也可以实现轻量级同步
     * 
     * b之前的写入(对应代码b = a)对读取b后的代码(print b)都可见，
     * 所以在writerThread里对a的赋值，一定会对readerThread里的读取可见，
     * 所以这里的a即使不加volatile，只要b读到是3，
     * 就可以有happens-before原则保证了读取到的都是3而不可能读取到1
     */
    
    // 模拟触发器
    int ab;
    int abc;
    int abcd;
    
    
    int a = 1;
    int b = 1;
    
    
    int c = 1;
    int d = 1;
//    volatile int b = 2;
    
    private void change() {
        a = 3;
        b = a;
        c = 5;

        /**
         * 同过synchronized的可见性原理，达到近朱者赤的目的，
         * 通过将最后一步操作加锁，保证了前几步操作对其他线程都是可见的
         */
        synchronized (this){
            d = 6;   
        }
    }

    private void print() {
        // 模拟触发器
//        if (b == 3){
//            System.out.println("abc = " + abc + "; ab = " + ab);
//        }

        /**
         * 同过synchronized的可见性原理，达到近朱者赤的目的，
         * 通过将最后一步操作加锁，保证了前几步操作对其他线程都是可见的
         */
        synchronized (this){
            int aa = a;
        }
        int bb = b;
        int cc = c;
        int dd = d;
        
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
