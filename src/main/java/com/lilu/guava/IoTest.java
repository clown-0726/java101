package com.lilu.guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class IoTest {
    public static void main(String[] args) throws IOException {
        // 创建 Source 和 Sink
        CharSource charSource = Files.asCharSource(new File("Source.txt"), Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(new File("Target.txt"), Charsets.UTF_8);

        // 拷贝
        charSource.copyTo(charSink);

        // Files 中有很多文件相关的方法
        // Files...
    }
}
