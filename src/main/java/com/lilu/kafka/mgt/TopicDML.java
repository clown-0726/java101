package com.lilu.kafka.mgt;

import com.lilu.utils.PropertiesReader;
import org.apache.kafka.clients.admin.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class TopicDML {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 工具类读取 resources 下的执行 properties 文件
        Properties propsConfig = PropertiesReader.getProperties("kafka.auth.properties");

        Properties props = new Properties();
        // 设置 Kafka 的 bootstrap server
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, propsConfig.getProperty("bootstrap.servers"));
        // 设置 API key 和 API secret
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='" + propsConfig.getProperty("username") + "' password='" + propsConfig.getProperty("password") + "';");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");

        // 声明管理类
        KafkaAdminClient adminClient = (KafkaAdminClient) KafkaAdminClient.create(props);
        System.out.println("Connected to Kafka");

        // 要加上返回值和后面的方法，否则是异步的
        CreateTopicsResult topic2create = adminClient.createTopics(Arrays.asList(new NewTopic("topic01", 2, (short) 3)));
        topic2create.all().get(); // 同步创建

        // 查看所有的 Topics
        ListTopicsResult topicsResult = adminClient.listTopics();
        Set<String> names = topicsResult.names().get();
        for (String name : names) {
            System.out.println(name);
        }

        // Describe Topics
        DescribeTopicsResult topic2describe = adminClient.describeTopics(Arrays.asList("topic01"));
        Map<String, TopicDescription> topicDescriptionMap = topic2describe.all().get();
        for (Map.Entry<String, TopicDescription> entry : topicDescriptionMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().name());
        }

        // 删除 topic
        DeleteTopicsResult topic2delete = adminClient.deleteTopics(Arrays.asList("topic01"));
        topic2delete.all().get(); // 同步删除

        adminClient.close();
    }
}
