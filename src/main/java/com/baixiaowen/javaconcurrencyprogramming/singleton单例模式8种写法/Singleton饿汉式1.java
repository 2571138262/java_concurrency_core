package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描述：          饿汉式：(静态常量)      [可用]
 * 
 * 优点：比较简单，在类加载的时候就已经完成了初始化
 */
public class Singleton饿汉式1 {
    
    private final static Singleton饿汉式1 INSTANCE = new Singleton饿汉式1();
    
    private Singleton饿汉式1(){}
    
    public static Singleton饿汉式1 getInstance(){
        return INSTANCE;
    }
    
}
