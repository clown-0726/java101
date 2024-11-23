package com.lilu.jsonpath;

import com.jayway.jsonpath.JsonPath;

import java.util.Date;

public class HelloJsonPath {
    public static void main(String[] args) {
        String json = "{\"date_as_long\" : 1411455611975}";
        Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
        System.out.println(date);
    }
}
