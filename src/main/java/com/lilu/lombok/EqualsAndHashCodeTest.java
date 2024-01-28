package com.lilu.lombok;

import lombok.EqualsAndHashCode;

// 生成 equals 和 hashCode 方法
@EqualsAndHashCode(
        exclude = {"field1"}
)
public class EqualsAndHashCodeTest {
    private String field1;
    private String field2;
}
