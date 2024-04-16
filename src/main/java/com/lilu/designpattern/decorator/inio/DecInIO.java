package com.lilu.designpattern.decorator.inio;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;

public class DecInIO {
    public static void main(String[] args) throws IOException {
        Enumeration<URL> urls = DecInIO.class.getClassLoader().getResources("abc123.txt");
        String filePath = urls.nextElement().getPath();
        System.out.println(filePath);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
