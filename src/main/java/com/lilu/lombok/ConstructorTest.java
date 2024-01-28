package com.lilu.lombok;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//@NoArgsConstructor // 自动生成无参数构造函数
//@AllArgsConstructor // 自动生成全参数构造函数
@RequiredArgsConstructor // 只针对要求非空参数生成构造函数（field1, field2）
public class ConstructorTest {
    private final String field1;

    @NonNull
    private String field2;

    private String field3;
}
