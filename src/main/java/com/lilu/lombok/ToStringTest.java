package com.lilu.lombok;

import lombok.ToString;

@ToString(
        includeFieldNames = false, // 是否包含变量名
        exclude = {"field1"}, // 排除属性
        of = {"field2"}, // 包含属性
        doNotUseGetters = true // 是否使用 Getter 方法
)
public class ToStringTest {
    private String field1;
    private String field2;
}
