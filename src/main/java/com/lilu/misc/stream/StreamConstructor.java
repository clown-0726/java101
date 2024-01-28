package com.lilu.misc.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamConstructor {
    public static void main(String[] args) {
        StreamConstructor streamConstructor = new StreamConstructor();
        //streamConstructor.streamFromValue();
        //streamConstructor.streamFromArray();
    }

    // 由数值构建流
    public void streamFromValue() {
        Stream stream = Stream.of(1, 2, 3, 4, 5);
        stream.forEach(System.out::println);
    }

    // 由数组构建流
    public void streamFromArray() {
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(numbers);
        intStream.forEach(System.out::println);
    }

    // 由文件构建流
    public void streamFromFile() throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get("pathxxx"));
        stringStream.forEach(System.out::println);
    }

    // 由函数构建流
    public void streamFromFunction() throws IOException {
        // 通过生成器
        Stream stream1 = Stream.generate(Math::random);
        stream1.limit(100).forEach(System.out::println);

        // 生成无限流
        Stream stream2 = Stream.iterate(0, n -> n + 2);
        stream2.forEach(System.out::println);
    }
}
