package com.lilu.inoutputstream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ReadFileLineByLine {
    public static void main(String[] args) {

        ClassLoader classLoader = ReadFileLineByLine.class.getClassLoader();
        URL url = classLoader.getResource("abc.txt");
        String filePath = url.getPath();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
