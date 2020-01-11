package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题.手写观察者模式;

public class Main {

    public static void main(String[] args) {
        // 初始化自定义监听器
        MyEventListener myEventListener = new MyEventListener(event -> {
            System.out.println("我是真正的监听器方法");
        });
        
        // 
        myEventListener.registeredMonitoring(new Event() {
        });
        
    }
    
}
