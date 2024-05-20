package com.lilu.designpattern.adapter.others;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadFileByLineDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("abc.txt");
        // InputStreamReader 就是 Adapter
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line !=null && !line.equals("")) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}
