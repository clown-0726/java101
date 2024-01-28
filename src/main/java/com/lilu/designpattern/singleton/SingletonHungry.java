package com.lilu.designpattern.singleton;

public class SingletonHungry {
    private final static SingletonHungry INSTANCE = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry getInstance() {
        return INSTANCE;
    }
}
