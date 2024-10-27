package com.lilu.misc.lambda;

public class FileService {
    public void fileHandler(String text, FileConsumer fileConsumer) {
        fileConsumer.fileHandler(text);
    }

    public static void main(String[] args) {
        FileService fileService = new FileService();
        fileService.fileHandler("I am java", txt -> System.out.println(txt));
    }
}
