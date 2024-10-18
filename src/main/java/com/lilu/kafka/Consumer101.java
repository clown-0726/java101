package com.lilu.kafka;

import com.lilu.utils.PropertiesReader;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

public class Consumer101 {
    public static void main(String[] args) {
        // 工具类读取 resources 下的执行 properties 文件
        Properties propsConfig = PropertiesReader.getProperties("kafka.auth.properties");

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, propsConfig.getProperty("bootstrap.servers"));
        // 设置 API key 和 API secret
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='" + propsConfig.getProperty("username") + "' password='" + propsConfig.getProperty("password") + "';");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        // 设置 key 和 value 的序列化类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 第二个参数表示当新的 Consumer 进组之后触发的均衡事件
        consumer.subscribe(Pattern.compile("^topic01.*"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
            }
        });

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            if (!records.isEmpty()) {
                // 打印一次拿到记录的所有分区信息，可以按照分区进行数据的遍历
                records.partitions().forEach(partition -> {
                    System.out.println(partition.partition());
                });

                // 直接进行所有数据的遍历
                Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
                while (iterator.hasNext()) {
                    ConsumerRecord<String, String> record = iterator.next();
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();

                    System.out.println(topic + ":" + partition + ":" + offset + ":" + record.key() + ":" + record.value());
                }
            }
        }
    }
}
