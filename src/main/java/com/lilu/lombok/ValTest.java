package com.lilu.lombok;

import lombok.val;

import java.util.ArrayList;

// 弱语言变量，会自动推测类型
public class ValTest {
    public ValTest() {
        val field = "zhangsan";
        val list = new ArrayList<String>();
        list.add(field);
    }
}
