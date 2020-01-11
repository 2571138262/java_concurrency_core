package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;

/**
 * 描述：                  用工厂模式修复刚才初始化问题 
 */
public class RegisteredMonitoringEnhance {


    int count ;
    
    private EventListener listener;

    private RegisteredMonitoringEnhance(MySource source) {
        listener = new EventListener() {
            @Override
            public void onEvent(Event o) {
                System.out.println("\n我得到的数字是 ： " + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static RegisteredMonitoringEnhance getInstance(MySource source){
        RegisteredMonitoringEnhance safeListener = new RegisteredMonitoringEnhance(source);
        source.registerListener(safeListener.listener);
        return safeListener;
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

        RegisteredMonitoringEnhance multiThreadsErrorRegisteredMonitoring = new RegisteredMonitoringEnhance(mySource);
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
