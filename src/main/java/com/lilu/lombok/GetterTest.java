package com.lilu.lombok;

import lombok.AccessLevel;
import lombok.Getter;

public class GetterTest {
    // 默认设置
    @Getter
    private String field1;

    // getter 方法访问权限为 private
    @Getter(
            value = AccessLevel.PRIVATE
    )
    private String field2;

    // final 修饰的常量懒加载
    @Getter(
            lazy = true
    )
    private final String field3 = "Zhangsan";
}
