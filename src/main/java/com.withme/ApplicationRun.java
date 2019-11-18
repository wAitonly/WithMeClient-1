package com.withme;

import com.withme.swing.ClientSwing;
import com.withme.handler.inbound.MessageReceive;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * 启动类
 * @author zhaobenquan 2019/10/15
 */
@SpringBootApplication
public class ApplicationRun {
    /**
     * 本客户端订阅的topic
     * @param args
     */
    private static final String topic = "to_WithMeClient";


    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ApplicationRun.class);
        builder.headless(false).run(args);
        //拿到该客户端应该订阅的所有topic，如何该客户端的好友、群组过多，过多的topic订阅如何处理
        List<String> topicList = new ArrayList<String>(){{
            add(topic);
        }};
        //启动界面客户端
        ClientSwing.start();
        //启动消息接收
        MessageReceive.start(topicList);
    }
}
