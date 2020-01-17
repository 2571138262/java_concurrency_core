package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描述：              静态内部类方式，推荐用  也是归属于懒汉模式
 * 
 *      由于JVM类加载的性质保证了，即便是多个线程同时去访问这个对象，它也不会建造出多个实例
 */
public class Singleton静态内部类7 {
    
    private Singleton静态内部类7(){}
    
    private static class SingletonInstance{
        private static final Singleton静态内部类7 INSTANCE = new Singleton静态内部类7();
    }
    
    private static Singleton静态内部类7 getInstance(){
        return SingletonInstance.INSTANCE;
    }
    
}
