package com.lilu.designpattern.builder.use1builder;

public class Client {
    public static void main(String[] args) {
        // 注意不能直接 new Product，这样使用类就高耦合了 Product 类
        Product director1 = new Product.Builder().setName("Xiaoming").setAge(18).build();
        System.out.println(director1.name);

        // Product.newBuilder() 得到构建者
        // build() 方法得到具体的产品类
        Product director2 = Product.newBuilder().setName("Xiaoming").setAge(18).build();
        System.out.println(director2.name);
    }
}
