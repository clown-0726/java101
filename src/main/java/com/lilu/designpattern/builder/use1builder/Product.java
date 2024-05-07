package com.lilu.designpattern.builder.use1builder;

public class Product {
    public String name;
    public int age;

    // 通过静态方法的到构建者
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * 下面是内聚的构建者 -----------------------------------------------------------
     * <p>
     * 高内聚了 Product 对象的创建以及加工方式，不需要外部使用方对其设置，封装了构建者本身对对象的加工方法
     * 低耦合了使用方 Client 和 Product 对象的加工方式
     * <p>
     */
    static class Builder {
        private String name;
        private int age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Product build() {
            // 根据提供的"原料"在这里进行复杂的构建
            Product director = new Product();
            director.name = this.name;
            director.age = this.age;
            return director;
        }
    }
}
