package com.withme.swing.actionlistener;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.withme.core.SingleChannel;
import com.withme.swing.ReceiveAreaTable;
import com.withme.swing.ReceiveAreaTableModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.*;

/**
 * 消息发送文本域监听事件
 * @author zhaobenquan 2019/11/18
 */
@AllArgsConstructor
public class SendArea implements KeyListener {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SendArea.class);

    /**
     * 消息发给谁
     * @param args
     */
    private static final String toTopic = "to_WithMeClient-2";
    /**
     * 输入内容
     */
    @Getter
    private static String inputStr;
    /**
     * 发送消息文本域
     */
    private JTextArea sendArea;
    /**
     * 消息发送线程池
     */
    private static ExecutorService singleThreadPool;

    static{
        //初始化消息发送线程池
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("MsgSend-thread-%d")
                .build();
        singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 敲击事件
     * 敲击事件中区分不了keyCode,将监听移至按下事件
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 键按下
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //crtl+enter换行
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER){
            //屏蔽回车事件
            e.consume();
            //换行
            this.sendArea.append("\n");
        }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            //屏蔽回车事件
            e.consume();
            //页面输入消息
            inputStr = sendArea.getText();
            //包装消息
            String msg = "{\"content\":\""+inputStr+"\",\"topic\":\""+toTopic+"\"}";
            //消息发送给服务端
            singleThreadPool.execute(()-> SingleChannel.getInstance().channel.writeAndFlush(msg));
            logger.info("已发送消息："+msg);
            //展示消息发送记录
            JMenuMsg.table.addModel(new ReceiveAreaTableModel("",inputStr,"3"));
            //情况输入文字域
            sendArea.setText("");
        }
    }

    /**
     * 键抬起
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
