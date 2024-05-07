package com.lilu.designpattern.builder.use2builder;

public class Director {
    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public void constructProduct() {
        builder.buildName("Xiaoming");
        builder.buildAge(18);
    }
}
