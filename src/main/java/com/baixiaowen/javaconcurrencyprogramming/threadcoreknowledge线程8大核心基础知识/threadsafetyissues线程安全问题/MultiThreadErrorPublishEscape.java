package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.threadsafetyissues线程安全问题;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：                  发布逸出
 */
public class MultiThreadErrorPublishEscape {
    
    private Map<String, String> states;

    public MultiThreadErrorPublishEscape() {
        this.states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }
    
    public Map<String, String> getStates(){
        return states;
    }

    // 返回一个副本
    public Map<String, String> getStatesImproved(){
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadErrorPublishEscape multiThreadErrorPublishEscape = new MultiThreadErrorPublishEscape();
        Map<String, String> states = multiThreadErrorPublishEscape.getStates();
//        System.out.println(states.get("1"));
//        states.remove("1");
//        System.out.println(states.get("1"));

        System.out.println(multiThreadErrorPublishEscape.getStatesImproved().get("1"));
        multiThreadErrorPublishEscape.getStatesImproved().remove("1");
        System.out.println(multiThreadErrorPublishEscape.getStatesImproved().get("1"));
    }
}
