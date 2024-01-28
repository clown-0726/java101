package com.lilu.guava;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) throws Throwable {
        /**
         * 三种创建 Optional 的方式
         */

        // 创建空的 Optional 对象
        Optional.empty();

        // 使用非 null 值创建 Optional 对象
        Optional.of("I am not allowed to null");

        // 使用任意值创建 Optional 对象
        Optional optional = Optional.ofNullable(null);

        // 判断是否为空，不建议使用
        optional.isPresent();

        optional.ifPresent(System.out::println);


        optional.orElse("引用缺失");
        optional.orElseGet(() -> {
            return "灵活的引用缺失";
        });
        optional.orElseThrow(()-> {
            throw new RuntimeException("引用缺失异常");
        });

    }
}
