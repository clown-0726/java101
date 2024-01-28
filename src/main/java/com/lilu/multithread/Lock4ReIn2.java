package com.lilu.multithread;


import java.util.concurrent.TimeUnit;

public class Lock4ReIn2 {
    synchronized void m() {
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new Lock4ReIn2Child().m();
    }
}

class Lock4ReIn2Child extends Lock4ReIn2 {
    @Override
    synchronized void m() {
        System.out.println("child m start");
        // 锁必须是可重入的，如果不可重入的话，那么在继承关系中，通过 super 调用父类的覆盖方法则会出现死锁
        // 注意这里调用父类的 m 方法，在父类的 m 方法上的锁也是 Lock4ReIn2Child 的当前实例 this
        super.m();
        System.out.println("child m end");
    }
}