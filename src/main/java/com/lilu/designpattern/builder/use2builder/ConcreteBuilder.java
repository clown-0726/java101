package com.lilu.designpattern.builder.use2builder;

public class ConcreteBuilder extends AbstractBuilder {
    private final Product product;

    public ConcreteBuilder() {
        this.product = new Product();
    }

    @Override
    AbstractBuilder buildName(String name) {
        this.product.setName(name);
        return this;
    }

    @Override
    AbstractBuilder buildAge(int age) {
        this.product.setAge(age);
        return this;
    }

    @Override
    Product build() {
        // 根据提供的"原料"在这里进行复杂的构建
        // ...
        return this.product;
    }
}
