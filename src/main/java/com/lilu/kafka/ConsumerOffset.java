package com.lilu.kafka;

import com.lilu.utils.PropertiesReader;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class ConsumerOffset {
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
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g2");
        // Offset 设置
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Pattern.compile("^topic01.*"));

        // 开始消费
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            if (!records.isEmpty()) {
                Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                while (iterator.hasNext()) {
                    ConsumerRecord<String, String> record = iterator.next();
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();

                    // 手动提交 Offset
                    // 设置 props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
                    //offsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset + 1));
                    //consumer.commitAsync(offsets, new OffsetCommitCallback() {
                    //    @Override
                    //    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                    //        System.out.println("offset commited!");
                    //    }
                    //});

                    System.out.println(topic + ":" + partition + ":" + offset + ":" + record.key() + ":" + record.value());
                }
            }
        }
    }
}
