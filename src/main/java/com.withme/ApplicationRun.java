package com.withme;

import com.withme.handler.inbound.MessageReceive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;

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
    }
}
