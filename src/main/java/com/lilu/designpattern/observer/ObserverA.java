package com.lilu.designpattern.observer;

public class ObserverA extends Observer {
    public ObserverA(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void update() {
        System.out.println("ObserverA update...");
    }
}
