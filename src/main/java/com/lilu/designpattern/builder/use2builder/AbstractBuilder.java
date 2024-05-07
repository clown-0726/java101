package com.lilu.designpattern.builder.use2builder;

public abstract class AbstractBuilder {
    abstract AbstractBuilder buildName(String name);

    abstract AbstractBuilder buildAge(int age);

    abstract Product build();
}
