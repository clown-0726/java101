package com.lilu.lombok;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 自动释放资源
public class CleanupTest {
    public void copyFile(String in, String out) throws IOException {
        @Cleanup FileInputStream fileInputStream = new FileInputStream(in);
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(out);

        int r;
        while ((r = fileInputStream.read()) != -1) {
            fileOutputStream.write(r);
        }
    }
}
