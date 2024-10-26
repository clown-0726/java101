package com.lilu.misc.anonymous;

public class AnonymousDemo {
    public void run() {
        System.out.println("I am parent class.");
    }

    public static void main(String[] args) {
        // anonymousDemo 这个实例已经不是 AnonymousDemo 的实例
        // 而是其子类，也就是一个匿名类的实例类
        AnonymousDemo anonymousDemo = new AnonymousDemo() {
            @Override
            public void run() {
                System.out.println("I am child class.");
            }
        };

        anonymousDemo.run();
    }
}
