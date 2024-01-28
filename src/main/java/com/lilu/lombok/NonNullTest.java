package com.lilu.lombok;

import lombok.NonNull;

// 生成非空检查
public class NonNullTest {
    public void NonNullTest(@NonNull String field) {
        System.out.println(field);
    }
}
