package com.baixiaowen.javaconcurrencyprogramming.deadlock死锁;

/**
 * 描述：                  转账时候遇到死锁，一旦打开注释，便会发生死锁
 */
public class TransferMoney implements Runnable{

    int flag = 1;
    
    static Account a = new Account(500);
    static Account b = new Account(500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        
        r1.flag = 1;
        r2.flag = 0;
        
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();

        System.out.println("a的余额" + a.balance);
        System.out.println("b的余额" + b.balance);
    }
    
    @Override
    public void run() {
        if (flag == 1){
            transferMoney(a, b, 200);
        }
        if (flag == 0){
            transferMoney(b, a, 200);
        }
    }
    
    public static void transferMoney(Account from, Account to, int amount){
        
        // 先获取要转账的对象的锁
        synchronized (from){

            // 模拟程序等待获取锁， 此时程序就会陷入死锁
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            
            // 然后再获取要转给的对象的锁
            synchronized (to){
                if (from.balance - amount < 0){
                    System.out.println("余额不足，转账失败。");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账 " + amount + "元");
            }
        }
    }
    
    static class Account{
        
        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }
    
}
