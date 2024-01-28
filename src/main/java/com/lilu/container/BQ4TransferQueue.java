package com.lilu.container;

import java.util.concurrent.LinkedTransferQueue;

public class BQ4TransferQueue {
    public static void main(String[] args) {
        LinkedTransferQueue<String> lq = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(lq.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        lq.put("abc");
    }
}
