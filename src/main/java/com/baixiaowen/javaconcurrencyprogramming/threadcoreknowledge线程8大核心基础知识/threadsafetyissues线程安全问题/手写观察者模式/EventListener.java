package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题.手写观察者模式;

/**
 * 观察者 接口
 */
public interface EventListener {
    
    // 监听器调用方法
    void onEvent(Event event);
    
}
