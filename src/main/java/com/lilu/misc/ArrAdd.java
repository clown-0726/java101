package com.lilu.misc;

import java.util.ArrayList;

public class ArrAdd {
    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<String>() {
            {
                add("abc");
                add("def");
            }
        };

        for (String str : strList) {
            System.out.println(str);
        }
    }
}
