package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadobjectclasscommonmethod线程相关重要方法;

/**
 * 描述：              线程ID从1开始，JVM运行起来后，我们自己创建的线程的ID早已不是2
 *          启动程序以后同时启动的线程：
 *                  Singal Dispatcher : 负责把操作系统的信号发给适当的程序  
 *                  Reference Handler : 是和GC引用相关的的线程
 *                  Finalizer         : 负责执行对象finalize的方法
 *                  ... 
 */
public class Id {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID为：" + Thread.currentThread().getId());
        System.out.println("子线程的ID为：" + thread.getId());
    }
    
}
