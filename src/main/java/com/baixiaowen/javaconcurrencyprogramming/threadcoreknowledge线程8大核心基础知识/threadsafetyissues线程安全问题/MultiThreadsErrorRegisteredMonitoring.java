package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;



/**
 * 描述：          观察者模式
 */
public class MultiThreadsErrorRegisteredMonitoring {
    
    int count ;

    public MultiThreadsErrorRegisteredMonitoring(MySource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event o) {
                System.out.println("\n我得到的数字是 ： " + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        
        MultiThreadsErrorRegisteredMonitoring multiThreadsErrorRegisteredMonitoring = new MultiThreadsErrorRegisteredMonitoring(mySource);
    }

    static class MySource{
        private EventListener listener;
        
        void registerListener(EventListener eventListener){
            this.listener = eventListener;
        }
        
        void eventCome(Event e){
            if (listener != null){
                listener.onEvent(e);
            }else{
                System.out.println("还未初始化完毕");
            }
        }
    }
    
    interface EventListener{
        void onEvent(Event o);
    }
    
    interface Event{
    }
    
}
