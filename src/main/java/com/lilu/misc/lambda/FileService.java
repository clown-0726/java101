package com.lilu.misc.lambda;

public class FileService {
    public void fileHandler(String text, FileConsumer fileConsumer) {
        fileConsumer.fileHandler(text);
    }
}
