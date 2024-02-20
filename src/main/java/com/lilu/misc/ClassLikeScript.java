package com.lilu.misc;

/**
 * 像运行脚本一样运行 java 代码，全部静态化
 */
public class ClassLikeScript {
    static String name = "ClassLikeScript";

    static {
        System.out.println("I am static method!");
    }

    static void changeName(String newName) {
        name = newName;
    }

    // 内部类
    static class Car {
        String door = "Steel";

        Car(String door) {
            this.door = door;
        }

        void run() {
            System.out.println("The car is running...");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main Start...");
        new Car("Steel").run();
        name = "ClassLikeScript New";
        ClassLikeScript.changeName("ClassLikeScript New...");
        System.out.println(ClassLikeScript.name);
    }
}
