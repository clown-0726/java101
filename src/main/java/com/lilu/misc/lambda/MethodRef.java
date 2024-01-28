package com.lilu.misc.lambda;

import java.util.function.Consumer;

public class MethodRef {
    public void test1() {
        Consumer<String> consumer1 = (String number) -> Integer.parseInt(number);
        // 指向静态方法的方法引用
        Consumer<String> consumer2 = Integer::parseInt;
    }

    public void test2() {
        StringBuilder stringBuilder = new StringBuilder();
        Consumer<String> consumer1 = (String str) -> stringBuilder.append(str);
        // 指向现有对象的实例方法的方湖马用
        Consumer<String> consumer2 = stringBuilder::append;
    }

    public void test3() {
        Consumer<String> consumer1 = (String str) -> str.length();
        // 指向任意类型实例方法的方法引用
        Consumer<String> consumer2 = String::length;
    }
}
