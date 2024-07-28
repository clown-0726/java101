package com.lilu.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SvcBroker {
    public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        /*
         * 简单的消息发送行为
         */
        String rabbitmqUir = "amqps://username:password@host:port";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(rabbitmqUir);

        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel();) {
            for (int i = 0; i < 100; i++) {
                // 可以设置消息的属性：过期时间
                AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("15000").build();
                channel.basicPublish("exchange.order", "key.order", properties, (i + "Hello").getBytes());
                System.out.println("send...");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
