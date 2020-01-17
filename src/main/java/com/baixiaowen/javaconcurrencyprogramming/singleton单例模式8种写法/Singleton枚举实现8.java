package com.baixiaowen.javaconcurrencyprogramming.singleton单例模式8种写法;

/**
 * 描述：          枚举实现 推荐用   生成实践中最佳的实现
 * 
 *              枚举反编译以后依然是一个类
 */
public enum  Singleton枚举实现8 {
    
    INSTANCE;
    
    public void whatever(){
        
    }
    
    public Singleton枚举实现8 getInstance(){
        return Singleton枚举实现8.INSTANCE;
    }

    public static void main(String[] args) {
        // 枚举单例的调用
        Singleton枚举实现8.INSTANCE.whatever();
    }
    
}
