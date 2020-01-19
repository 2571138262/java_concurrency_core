package com.baixiaowen.javaconcurrencyprogramming.deadlock死锁;

import java.util.Random;

/**
 * 描述：                  多人同时转账，依然很危险
 */
public class MultiTransferMoney {
    // 定义账户数
    private static final int NUM_ACCOUNTS = 500;
    // 定义账户余额
    private static final int NUM_MONEY = 1000;
    // 定义每个线程转账次数
    private static final int NUM_ITERATIONS = 1000000;
    // 定义同时有多少个线程
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random rnd = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }

        // 定义账户线程
        class TransferThread extends Thread{
            @Override
            public void run() {
                // 模拟一个线程转账1000000
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
                System.out.println("所有线程运行结束");
            }
        }
        
        // 假设同时又20个人同时操作者500个账户来转账
        for (int i = 0; i < NUM_THREADS; i++) {
             new TransferThread().start();
        }
        
    }
    
    
}
