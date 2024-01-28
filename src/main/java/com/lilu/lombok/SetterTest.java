package com.lilu.lombok;

import lombok.AccessLevel;
import lombok.Setter;

public class SetterTest {
    // 默认设置
    @Setter
    private String field1;

    // setter 函数的访问等级
    @Setter(
            value = AccessLevel.PRIVATE
    )
    private String field2;
}
