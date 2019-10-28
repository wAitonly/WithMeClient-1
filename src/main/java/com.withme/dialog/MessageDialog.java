package com.withme.dialog;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.withme.core.SingleChannel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * 对话框
 * @author zhaobenquan
 */
public class MessageDialog extends JFrame {
    private Logger logger = LoggerFactory.getLogger(MessageDialog.class);

    private static JTextField input;
    public static Optional<JTextArea> show;

    /**
     * 输入内容
     */
    @Getter
    private String inputStr;

    /**
     * 构造消息对话框
     */
    MessageDialog(){
        //初始化消息发送线程池
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("MsgSend-start-thread-%d")
                .build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        //设置组件
        setLayout(new FlowLayout());
        //页面输入组件
        input = new JTextField(10);
        //展示消息组件
        show = Optional.of(new JTextArea(9,20));
        show.ifPresent(e -> show.get().append("MessageRecord\n"));
        input.addActionListener(e -> {
            //页面输入消息
            inputStr = input.getText();
            //包装消息
            String msg = "{\"content\":\""+inputStr+"\",\"topic\":\"to_WithMeClient-2\"}";
            //消息发送给服务端
            singleThreadPool.execute(()-> SingleChannel.getInstance().channel.writeAndFlush(msg));
            logger.info("已发送消息："+msg);
            //展示发送记录
            show.get().append("me："+inputStr+"\n");
        });
        add(input,BorderLayout.EAST);
        add(show.get(),BorderLayout.CENTER);
        setVisible(true);
        setBounds(100,100,400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 启动界面
     */
    public static void start(){
        new MessageDialog();
    }

    /**
     * 展示接收消息
     * @param msg
     */
    public static void showReceiveMsg(String msg){
        show.ifPresent(e -> show.get().append("other:"+msg+"\n"));
    }
}
