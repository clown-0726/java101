package com.lilu.designpattern.adapter.adaptercompose;

public class Adapter {
    Adaptee adaptee = new Adaptee();

    public int provider10() {
        return adaptee.provider100() / 10;
    }
}
