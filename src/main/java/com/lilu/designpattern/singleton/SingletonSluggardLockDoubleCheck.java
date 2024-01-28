package com.lilu.designpattern.singleton;

public class SingletonSluggardLockDoubleCheck {
    private static /*volatile*/ SingletonSluggardLockDoubleCheck INSTANCE = null;

    private SingletonSluggardLockDoubleCheck() {
    }

    public static SingletonSluggardLockDoubleCheck getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonSluggardLockDoubleCheck.class) {
                if (INSTANCE == null) {
                    /**
                     * 1. 申请内存空间
                     * 2. 初始化对象成员变量
                     * 3. 将对象赋值给 INSTANCE
                     */
                    INSTANCE = new SingletonSluggardLockDoubleCheck();
                }
            }
        }
        return INSTANCE;
    }
}
