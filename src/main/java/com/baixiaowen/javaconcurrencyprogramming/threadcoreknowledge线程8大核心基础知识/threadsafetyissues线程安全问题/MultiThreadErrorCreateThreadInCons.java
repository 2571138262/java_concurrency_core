package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：          构造函数中新建线程
 */
public class MultiThreadErrorCreateThreadInCons {

    private Map<String, String> states;

    public MultiThreadErrorCreateThreadInCons() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1", "周一");
                states.put("2", "周二");
                states.put("3", "周三");
                states.put("4", "周四");
            }
        }).start();
    }

    public Map<String, String> getStates(){
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadErrorCreateThreadInCons multiThreadErrorCreateThreadInCons = new MultiThreadErrorCreateThreadInCons();
        Thread.sleep(1000);
        Map<String, String> states = multiThreadErrorCreateThreadInCons.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
    
}
