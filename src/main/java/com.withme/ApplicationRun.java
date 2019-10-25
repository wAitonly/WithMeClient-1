package com.withme;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.withme.core.SingleChannel;
import com.withme.handler.inbound.MessageReceive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 启动类
 * @author zhaobenquan 2019/10/15
 */
@SpringBootApplication
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class);
        //拿到该客户端应该订阅的所有topic，如何该客户端的好友、群组过多，过多的topic订阅如何处理
        List<String> topicList = new ArrayList<String>(){{
            add("to_WithMeClient");
        }};
        //启动消息接收
        MessageReceive.start(topicList);
        //发送消息
        String msg = "{\"content\":\"this msg is client 1 to 2\",\"topic\":\"to_WithMeClient-2\"}";
        System.out.println("--"+msg);

        //消息发送线程
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("MsgSend-start-thread-%d")
                .build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(()-> SingleChannel.getInstance().channel.writeAndFlush(msg));
    }
}
