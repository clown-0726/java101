package com.lilu.designpattern.singleton;

public class SingletonSluggard {
    private static SingletonSluggard INSTANCE = null;

    private SingletonSluggard() {
    }

    public static SingletonSluggard getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonSluggard();
        }
        return INSTANCE;
    }
}
