package com.lilu.designpattern.template;

public class Process {
    final public void templateProcess() {
        System.out.println("I am step1 from Process.");
        step2();
    }

    public void step2() {
        throw new UnsupportedOperationException();
    }

    public void step3() {
        System.out.println("I am step3 from Process.");
    }
}
