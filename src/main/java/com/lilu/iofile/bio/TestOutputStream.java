package com.lilu.iofile.bio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


// https://blog.csdn.net/qq_72935001/article/details/128664976

public class TestOutputStream {
    public static void main(String[] args) {

        System.out.println("I am output stream");

        String filePath = "temp.txt";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write("I am good!".getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
