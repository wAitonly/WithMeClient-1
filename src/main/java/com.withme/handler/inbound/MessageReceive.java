package com.withme.handler.inbound;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.withme.mq.ConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;

/**
 * kafka订阅topic消息接收
 * @author zhaobenquan 2019/10/22
 */
public class MessageReceive {
    private static final Logger logger = LoggerFactory.getLogger(MessageReceive.class);

    /**
     * 全局消费者
     */
    private static KafkaConsumer<String,String> consumer;
    /**
     * 全局消息接收
     */
    private static ConsumerRecords<String, String> records;

    /**
     * 启动消息接收
     * @param topicList
     */
    public static void start(List<String> topicList){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("MsgReceive-start-thread-%d")
                .build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(()-> {
            //kafaka收消息
            consumer = ConsumerFactory.openConsumer();
            //订阅topic
            consumer.subscribe(topicList);
            for(;;) {
                records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("offset = "+record.offset()+", value = "+record.value());
                }
            }
        });
        logger.info("消息接收开启成功");
    }
}
