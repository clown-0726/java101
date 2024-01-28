package com.lilu.designpattern.observer;

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.attach(new ObserverA(subject));
        subject.attach(new ObserverB(subject));

        subject.notifyAllObservers();
    }
}
