package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描 述：          懒汉式（线程不安全） [不可用]
 */
public class Singleton懒汉式线程不安全3 {
    
    private static Singleton懒汉式线程不安全3 instance;

    private Singleton懒汉式线程不安全3() {
    }
    
    public static Singleton懒汉式线程不安全3 getInstance(){
        if (instance == null){
            instance = new Singleton懒汉式线程不安全3();
        }
        return instance;
    }
}
