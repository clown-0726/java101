package com.lilu.designpattern.adapter.adapterextend;

public class Adapter extends Adaptee {
    public int provider10() {
        return provider100() / 10;
    }
}
