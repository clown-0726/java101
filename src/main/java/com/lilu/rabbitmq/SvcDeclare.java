package com.lilu.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SvcDeclare {
    public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        /*
         * 声明 exchange & queue
         * 声明绑定关系（direct/fanout/topic）
         */
        String rabbitmqUir = "amqps://username:password@host:port";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(rabbitmqUir);

        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel();) {
            channel.exchangeDeclare("exchange.order", BuiltinExchangeType.DIRECT, true, false, null);
            channel.queueDeclare("queue.order", true, false, false, null);
            channel.queueBind("queue.order", "exchange.order", "key.order");

            // 设置过期时间和死信队列
            //Map<String, Object> qArgs = new HashMap<>();
            //qArgs.put("x-message-ttl", 15000);
            //qArgs.put("x-dead-letter-exchange", "dlx.exchange");
            //
            //channel.queueDeclare("queue.order", true, false, false, qArgs);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
