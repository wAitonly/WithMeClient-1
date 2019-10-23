package com.withme.mq;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * 消费者工厂
 * @author zhaobenquan 2019/10/17
 */
public class ConsumerFactory {

    /**
     * 构造消费者
     * @return
     */
    public static KafkaConsumer<String, String> openConsumer(){
        Properties properties = new Properties();
        //xxx是服务器集群的ip
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "jd-group");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(properties);
    }
}
