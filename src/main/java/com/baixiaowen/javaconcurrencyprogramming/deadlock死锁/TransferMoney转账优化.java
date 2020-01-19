package com.baixiaowen.javaconcurrencyprogramming.deadlock死锁;

/**
 * 描述：              代码优化， 避免死锁
 */
public class TransferMoney转账优化 implements Runnable {


    int flag = 1;

    static Account a = new Account(500);
    static Account b = new Account(500);
    
    // 多个锁对象的hash值相等的时候，会调用这个lock
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TransferMoney转账优化 r1 = new TransferMoney转账优化();
        TransferMoney转账优化 r2 = new TransferMoney转账优化();

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
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {

        /**
         * 优化：          添加帮助类
         */

        class Helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账 " + amount + "元");
            }
        }

        // 获取 转入账户 和 转出账户 的 hash值
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        /**
         * 通过判断俩个或者多个锁对象的hash值大小，来规定加锁的先后顺序， 这样就能有效额避免死锁 
         */

        // 如果转出账户的hash值 小于 转入账户的hash值， 那么加锁的顺序就都按这个顺序来， 现获取到from锁，再获取to锁
        if (fromHash < toHash) {
            // 先获取要转账的对象的锁
            synchronized (from) {
                // 然后再获取要转给的对象的锁
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        }
        // 如果转出账户的hash值 大于 转入账户的hash值， 先获取到to锁，再获取from锁
        else if (fromHash > toHash) {
            // 先获取要转账的对象的锁
            synchronized (to) {
                // 然后再获取要转给的对象的锁
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        }
        // 如果上两种情况没走进去，说明两个对象的hash值相等，发生了hash冲突
        else{
            // 人为设置一个加时赛， 谁先获取到lock锁，谁先执行后边的加锁操作，后边加锁操作的先后顺序无所谓
            synchronized (lock){ 
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    static class Account {

        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }


}
