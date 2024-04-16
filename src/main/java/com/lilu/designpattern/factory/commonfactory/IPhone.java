package com.lilu.designpattern.factory.commonfactory;

public class IPhone implements Phone {
    public IPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make iphone!");
    }
}
