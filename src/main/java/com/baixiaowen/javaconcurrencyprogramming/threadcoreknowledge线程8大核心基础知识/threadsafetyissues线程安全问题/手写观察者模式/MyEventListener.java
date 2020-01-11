package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题.手写观察者模式;

/**
 * 自定义监听器
 */
public class MyEventListener {
    
    private EventListener eventListener;

    public MyEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    
    
    void registeredMonitoring(Event e){
        if (null != e){
            eventListener.onEvent(e);
        }else {
            System.out.println("被监听对象为null");
        }
    }
}
