package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.stopthread停止线程的正确方法3.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 描述：          演示volatile的局限性 part2
 *                  当陷入阻塞的时候， volatile是无法停止线程的
 *          此例中，生产者的生产速度很快，消费者的消费速度很慢
 *          所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        
        // 新建生产者
        Producer producer = new Producer(storage);
        
        Thread producerThread = new Thread(producer);
        
        producerThread.start();
        
        Thread.sleep(2000);
        
        // 新建消费者
        Consumer consumer = new Consumer(storage);
        
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多的数据了");
        // 一旦消费者不需要更多数据了，我们应该让生产者也停下来
        // 但是实际情况
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{
    
    // 阻塞队列
    BlockingQueue storage;
    
    // 标记位
    public volatile boolean canceled = false;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数，被放入到仓库中了");
                    storage.put(num);
                }
                num++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("生产者结束运行");
        }
    }
}

/**
 * 消费者
 */
class Consumer{
    // 阻塞队列
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }
    
    public boolean needMoreNums(){
        if (Math.random() > 0.95){
            return false;
        }
        return true;
    }
}