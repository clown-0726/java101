package com.lilu.oop.whyinterface;

public class Desktop implements Computer {
    @Override
    public String getContent(String param) {
        return "Get content from Desktop: " + param;
    }
}
