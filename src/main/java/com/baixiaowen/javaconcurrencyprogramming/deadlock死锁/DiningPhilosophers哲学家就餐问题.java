package com.baixiaowen.javaconcurrencyprogramming.deadlock死锁;

/**
 * 描述：                      演示哲学家就餐问题导致的死锁
 */
public class DiningPhilosophers哲学家就餐问题 {

    /**
     * 哲学家类， 每天重复做俩件事： 思考 和 吃饭
     */
    public static class Philosopher implements Runnable {
        
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 思考
                    doAction("Thingking");
                    // 吃饭
                    // 拿起左边筷子
                    synchronized (leftChopstick) {
                        doAction("Picked up left chopstick");
                        // 拿起右边筷子
                        synchronized (rightChopstick) {
                            doAction("Picked up right chopstick - eating;");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " : " + action);

            Thread.sleep((long) (Math.random() * 100));
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        
        Object[] chopsticks = new Object[philosophers.length];

        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) / chopsticks.length];
            
            // 解决死锁问题
            /**
             * 如果到了最后一位哲学家， 让这个哲学家获取筷子(锁)的顺序与其他人不一样， 这样就能有效的避免环路的形成
             */
            if (i == philosophers.length - 1){
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            }else {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }
            new Thread(philosophers[i], "哲学家 " + (i + 1) + "号").start();
        }
    }

}
