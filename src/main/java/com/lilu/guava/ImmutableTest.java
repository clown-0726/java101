package com.lilu.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        // 通过 jdk 提供的方法创建不可变类型
        List<String> newStringList = Collections.unmodifiableList(stringList);

        // Guava: 通过已经存在的集合创建
        ImmutableSet.copyOf(stringList);

        // Guava: 通过初始值，直接创建不可变集合
        ImmutableSet immutableSet = ImmutableSet.of('1', '2', '3');

        // Guava: 以 builder 方式创建
        ImmutableSet.builder()
                .add(1)
                .addAll(Sets.newHashSet(2, 3))
                .add(4)
                .build();

    }
}
