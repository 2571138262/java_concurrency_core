package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描述：              懒汉式（线程安全）  [不推荐] 
 *              效率太低了
 */
public class Singleton懒汉式线程安全不推荐4 {
    
    private static Singleton懒汉式线程安全不推荐4 instance;
    
    private Singleton懒汉式线程安全不推荐4(){}
    
    public synchronized static Singleton懒汉式线程安全不推荐4 getInstance(){
        if (instance == null){
            instance = new Singleton懒汉式线程安全不推荐4();
        }
        return instance;
    }
    
}
