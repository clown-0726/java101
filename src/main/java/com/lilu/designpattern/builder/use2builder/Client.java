package com.lilu.designpattern.builder.use2builder;

public class Client {
    public static void main(String[] args) {
        // 创建产品的构建者
        AbstractBuilder builder = new ConcreteBuilder();
        // 通过指挥者 Director 来构建复杂的产品（可以省略指挥者）
        Director director = new Director(builder);
        director.constructProduct();
        // 的到产品
        Product product = builder.build();
        System.out.println(product);
    }
}
