package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.uncaughtexception未捕获异常如何处理;

/**
 * 描述：
 *  1、不加 try catch 抛出4个异常， 都带线程名字
 *  2、加了try catch， 期望捕获到第一个线程的异常，线程234不应该运行， 希望看到打印出 Caught Exception
 *  3、执行时发现，根本没有Caught Exception， 线程234依然运行并且抛出异常
 *  
 *  说明线程的异常不能用传统方法捕获
 */
public class CantCatchDirectly implements Runnable{

    public static void main(String[] args) throws InterruptedException{
        try{
            new Thread(new CantCatchDirectly(), "Mythread - 1").start();
            Thread.sleep(300);

            new Thread(new CantCatchDirectly(), "Mythread - 2").start();
            Thread.sleep(300);

            new Thread(new CantCatchDirectly(), "Mythread - 3").start();
            Thread.sleep(300);

            new Thread(new CantCatchDirectly(), "Mythread - 4").start();
            Thread.sleep(300);   
        }catch (RuntimeException e){
            System.out.println("Caught Exception");
        }
    }
    
    @Override
    public void run() {
        try {
            throw new RuntimeException();   
        }catch (RuntimeException e){
            System.out.println("Caught Exception");
        }
    }
}
