package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.stopthread停止线程的正确方法3;

/**
 * 描述：                  最佳实践： catch了InterruptedException之后的优先选择：在方法签名中抛出这个异常
 * 那么run()就会强制try/catch
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                // 保存日志，停止程序
                System.out.println("保存日志");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException{
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
