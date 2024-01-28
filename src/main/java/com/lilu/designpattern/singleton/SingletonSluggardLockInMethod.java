package com.lilu.designpattern.singleton;

public class SingletonSluggardLockInMethod {
    private static SingletonSluggardLockInMethod INSTANCE = null;

    private SingletonSluggardLockInMethod() {
    }

    public static synchronized SingletonSluggardLockInMethod getInstance() {
        // 业务代码也被封装在锁中了
        if (INSTANCE == null) {
            INSTANCE = new SingletonSluggardLockInMethod();
        }
        return INSTANCE;
    }
}
