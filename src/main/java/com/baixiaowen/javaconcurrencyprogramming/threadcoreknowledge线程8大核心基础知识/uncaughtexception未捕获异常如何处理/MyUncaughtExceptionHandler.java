package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.uncaughtexception未捕获异常如何处理;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 描述：          自己的UncaughtExceptionHandler类， 用来处理线程的未捕获异常
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 日志对象
        Logger logger = Logger.getAnonymousLogger();
        // 打印日志
        logger.log(Level.WARNING, "线程异常，终止啦 " + t.getName(), e);
        
        System.out.println(name + "捕获了异常  " + t.getName() + " 异常: " + e);
    }
}
