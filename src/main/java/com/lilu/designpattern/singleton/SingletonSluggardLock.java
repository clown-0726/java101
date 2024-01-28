package com.lilu.designpattern.singleton;

public class SingletonSluggardLock {
    private static SingletonSluggardLock INSTANCE = null;

    private SingletonSluggardLock() {
    }

    public static SingletonSluggardLock getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonSluggardLock.class) {
                INSTANCE = new SingletonSluggardLock();
            }
        }
        return INSTANCE;
    }
}
