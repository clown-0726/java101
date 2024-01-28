package com.lilu.misc.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CloseStream {
    public void copyFile(String in, String out) {
        // 通过在 try 小括号中这种语法糖声明的方式可以自动关闭流
        // 其实代码会翻译成 final 关闭流的方式
        try (
                FileInputStream fileInputStream = new FileInputStream(in);
                FileOutputStream fileOutputStream = new FileOutputStream(out);
        ) {
            int r;
            while ((r = fileInputStream.read()) != -1) {
                fileOutputStream.write(r);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
