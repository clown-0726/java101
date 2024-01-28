package com.lilu.multithread;

import java.util.concurrent.TimeUnit;

public class BalanceExample {
    String name;
    double balance;

    public synchronized void set(String name, double balance) throws InterruptedException {
        this.name = name;
        Thread.sleep(2000);
        this.balance = balance;
    }

    public synchronized double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) throws InterruptedException {
        // 这用的 this 锁，虽然两个代码块不在一起，但是一并给上了锁
        // 锁锁定的是锁本身
        BalanceExample balanceExample = new BalanceExample();
        new Thread(() -> {
            try {
                balanceExample.set("Zhangsan", 100.0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(balanceExample.getBalance("Zhangsan"));

        TimeUnit.SECONDS.sleep(2);
        System.out.println(balanceExample.getBalance("Zhangsan"));
    }
}
