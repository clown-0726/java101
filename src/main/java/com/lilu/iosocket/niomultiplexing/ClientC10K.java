package com.lilu.iosocket.niomultiplexing;

import java.io.*;
import java.net.Socket;

public class ClientC10K {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 9000);
            client.setSendBufferSize(20);
            client.setTcpNoDelay(true);
            OutputStream outputStream = client.getOutputStream();

            InputStream inputStream = System.in;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        outputStream.write(b);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
