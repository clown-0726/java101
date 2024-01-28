package com.lilu.misc.lambda;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        fileService.fileHandler("I am java", txt -> System.out.println(txt));
    }
}
