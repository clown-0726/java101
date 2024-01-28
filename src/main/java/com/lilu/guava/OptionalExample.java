package com.lilu.guava;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalExample {
    public static void main(String[] args) {
        List<String> stringList = null;

        // List<String> stringList = new ArrayList<>();
        // stringList.add("123");

        Optional.ofNullable(stringList)
                // 将 List 转成 stream
                .map(List::stream)
                // 如果为 null 的话创建一个空的 stream 对象，就不会执行下面的操作了
                .orElseGet(Stream::empty)
                // 正常走 stream 的逻辑
                .forEach(System.out::println);

        // 如果 stringList 是 null 则报空指针异常
        stringList.stream().forEach(System.out::println);
    }
}
