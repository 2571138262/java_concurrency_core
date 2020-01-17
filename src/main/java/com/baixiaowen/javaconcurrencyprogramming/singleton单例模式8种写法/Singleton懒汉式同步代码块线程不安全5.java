package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描述：              懒汉式（线程不安全） [不可用]
 */
public class Singleton懒汉式同步代码块线程不安全5 {
    
    private static Singleton懒汉式同步代码块线程不安全5 instance;
    
    private Singleton懒汉式同步代码块线程不安全5(){}
    
    public static Singleton懒汉式同步代码块线程不安全5 getInstance(){
        // 俩个线程同时走过这条if语句， 还是会创建俩个实例，所以它是线程不安全的
        if (instance == null){ 
            synchronized (Singleton懒汉式同步代码块线程不安全5.class){
                instance = new Singleton懒汉式同步代码块线程不安全5();
            }
        }
        return instance;
    }
    
}
