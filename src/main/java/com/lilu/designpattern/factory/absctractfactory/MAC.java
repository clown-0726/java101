package com.lilu.designpattern.factory.absctractfactory;

public class MAC implements PC {
    public MAC() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make MAC!");
    }
}
