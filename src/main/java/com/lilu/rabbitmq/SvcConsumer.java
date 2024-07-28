package com.lilu.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SvcConsumer {
    static Channel channel;

    public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        String rabbitmqUir = "amqps://username:password@host:port";

        // 回调函数
        DeliverCallback deliverCallback = ((customerTag, message) -> {
            System.out.println(message);
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);

            // 消息重新回队列，不推荐使用
            //channel.basicNack(message.getEnvelope().getDeliveryTag(), false, true);
        });

        // ----------------
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(rabbitmqUir);

        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();

            // 在发送之前设置 prefetchCount，表示一个客户端一次最多消费几个消息，消费端限流
            //channel.basicQos(2);

            //channel.basicConsume("queue.order", true, deliverCallback, customerTag -> {});
            // 设置手动 ACK
            channel.basicConsume("queue.order", false, deliverCallback, customerTag -> {
            });

            // 设置无限循环，保持注册状态
            while (true) {
                Thread.sleep(1000);
            }
        } catch (IOException | TimeoutException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
