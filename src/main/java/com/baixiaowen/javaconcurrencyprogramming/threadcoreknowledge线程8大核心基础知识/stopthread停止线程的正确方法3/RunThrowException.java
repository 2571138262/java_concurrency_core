package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.stopthread停止线程的正确方法3;

/**
 * 描述：                  run方法无法抛出checked Exception，只能用try/catch
 */
public class RunThrowException {
    
    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
    
}
