package com.lilu.kafka;

import com.lilu.utils.PropertiesReader;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;

public class Consumer2Partition {
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

        // 指定消费者消费一个特定的分区
        TopicPartition topicPartition = new TopicPartition("topic01", 0);
        List<TopicPartition> partitions = Arrays.asList(topicPartition);
        consumer.assign(partitions);
        // 指定要检索的位置
        consumer.seekToBeginning(partitions);
        //consumer.seek(topicPartition, 1);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            if (!records.isEmpty()) {
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
