package com.lilu.designpattern.observer;

public class ObserverB extends Observer {
    public ObserverB(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void update() {
        System.out.println("ObserverB update...");
    }
}
