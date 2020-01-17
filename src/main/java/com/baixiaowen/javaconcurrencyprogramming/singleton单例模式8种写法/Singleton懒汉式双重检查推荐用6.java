package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描述：          双重检查（推荐免试使用）
 */
public class Singleton懒汉式双重检查推荐用6 {
    
    private volatile static Singleton懒汉式双重检查推荐用6 instance;
    
    private Singleton懒汉式双重检查推荐用6(){}
    
    public static Singleton懒汉式双重检查推荐用6 getInstance(){
        if (instance == null){
            synchronized (Singleton懒汉式双重检查推荐用6.class){
                if (instance == null){
                    instance = new Singleton懒汉式双重检查推荐用6();
                }
            }
        }
        return instance;
    }
    
}
