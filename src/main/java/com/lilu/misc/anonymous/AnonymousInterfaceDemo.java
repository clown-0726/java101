package com.lilu.misc.anonymous;

public class AnonymousInterfaceDemo {
    public static void main(String[] args) {
        AnonymousInterface anonymousInterface = new AnonymousInterface() {
            @Override
            public void run() {
                System.out.println("I am anonymous interface.");
            }
        };

        anonymousInterface.run();
    }
}
