package com.lilu.designpattern.template;

public class ConcreteProcess extends Process {
    @Override
    public void step2() {
        System.out.println("I am step2 from ConcreteProcess.");
        step3();
    }

    public static void main(String[] args) {
        ConcreteProcess cp = new ConcreteProcess();
        cp.templateProcess();
    }
}
