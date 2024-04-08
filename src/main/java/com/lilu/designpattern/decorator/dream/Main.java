package com.lilu.designpattern.decorator.dream;

public class Main {
    public static void main(String[] args) {
        AnyThing t1 = new Moon(new Dream(new You(null)));
        t1.exec();

        AnyThing t2 = new Dream(new Moon(new You(null)));
        t2.exec();
    }
}
