package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.stopthread停止线程的正确方法3;

/**
 * 描述：                  最佳实践2： 在catch字语句中调用Thread.currentThread().interrupt()来恢复中断状态，
 * 以便在后续的执行中，依然能够检查到刚才发生了中断
 * 回到RightWayStopThreadInProd 中补上中断， 让它跳出
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted， 程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();
            // 保存日志，停止程序
            System.out.println("保存日志");
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
