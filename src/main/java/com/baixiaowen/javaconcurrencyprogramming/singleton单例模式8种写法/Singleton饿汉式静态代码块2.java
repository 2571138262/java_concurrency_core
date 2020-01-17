package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描述：          饿汉式：(静态常量)      [可用]
 * 
 * 优点：比较简单，在类加载的时候就已经完成了初始化
 */
public class Singleton饿汉式静态代码块2 {
    
    private final static Singleton饿汉式静态代码块2 INSTANCE;
    
    static {
        INSTANCE = new Singleton饿汉式静态代码块2();
    }
    
    private Singleton饿汉式静态代码块2(){}
    
    public static Singleton饿汉式静态代码块2 getInstance(){
        return INSTANCE;
    }
    
}
