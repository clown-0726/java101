package com.lilu.designpattern.decorator.dream;

public class Main {
    public static void main(String[] args) {
        System.out.println("------ 明月装饰了 梦装饰了 生活");
        AnyThing t1 = new Moon(new Dream(new Life(null)));
        t1.exec();

        System.out.println("------ 梦装饰了 明月装饰了 生活");
        AnyThing t2 = new Dream(new Moon(new Life(null)));
        t2.exec();

        System.out.println("------ 生活装饰了 梦装饰了 明月");
        AnyThing t3 = new Life(new Dream(new Moon(null)));
        t3.exec();
    }
}
