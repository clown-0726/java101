package com.lilu.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SvcBrokerConfirmation {
    public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        /*
         * 消息发送的确认行为
         * 1. 第一种确认行为：消息是否已经被接收
         * 2. 第二种确认行为：消息是否已经被成功路由
         */
        String rabbitmqUir = "amqps://username:password@host:port";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(rabbitmqUir);

        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel1 = connection.createChannel();
                Channel channel2 = connection.createChannel();
                Channel channel3 = connection.createChannel();
        ) {
            // 同步确认机制 ---
            channel1.confirmSelect();
            channel1.basicPublish("exchange.order", "key.order", null, "Hello".getBytes());
            if (channel1.waitForConfirms()) {
                System.out.println("Message confirmed!");
            }

            // 异步确认机制 ---
            ConfirmListener confirmListener = new ConfirmListener() {
                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    System.out.println("Message confirmed! handleAck");
                }

                @Override
                public void handleNack(long l, boolean b) throws IOException {
                    System.out.println("Message confirmed! handleNack");
                }
            };
            channel2.addConfirmListener(confirmListener);
            channel2.basicPublish("exchange.order", "key.order", null, "Hello".getBytes());

            // 路由不成功返回 ---
            channel3.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode,
                                         String replyText,
                                         String exchange,
                                         String routingKey,
                                         AMQP.BasicProperties properties,
                                         byte[] body) throws IOException {
                    // 这里处理返回的消息
                    System.out.println("Message returned: " + new String(body));
                    System.out.println("Reply Text: " + replyText);
                    System.out.println("Reply Code: " + replyCode);
                    System.out.println("Exchange: " + exchange);
                    System.out.println("Routing Key: " + routingKey);
                }
            });
            // 第三个参数是 true，表示如果路由不成功则触发返回函数。
            // 没有这个 key.orderfake key，因此不会路由成功。
            channel3.basicPublish("exchange.order", "key.orderfake", true, null, "Hello".getBytes());

            // 睡眠
            Thread.sleep(2000);
        } catch (IOException | TimeoutException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
