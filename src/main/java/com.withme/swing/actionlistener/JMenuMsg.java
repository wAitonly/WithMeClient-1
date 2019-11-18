package com.withme.swing.actionlistener;

import com.withme.swing.FontType;
import lombok.AllArgsConstructor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 导航栏我的消息监听事件
 * @author zhaobenquan 2019/11/18
 */
@AllArgsConstructor
public class JMenuMsg implements MouseListener {
    /**
     * 基础面板
     */
    private JFrame jFrame;

    /**
     * 接收消息文本域
     */
    public static JTextArea receiveArea;

    @Override
    public void mouseClicked(MouseEvent e) {
        show();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * 展示我的消息面板
     */
    public void show(){
        //我的消息子面板
        //左边好友列表部分
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new FlowLayout());
        //右边消息框分发送和接收消息两部分
        //两个文本域分别用于发送和接收消息
        receiveArea = new JTextArea();
        JTextArea sendArea = new JTextArea();
        //设置聊天字体
        receiveArea.setFont(FontType.MESSAGE.getFont());
        //设置自动换行
        receiveArea.setLineWrap(true);
        //设置和换行不断字
        receiveArea.setWrapStyleWord(true);
        //设置为不可选中
        receiveArea.setEditable(false);
        sendArea.setFont(FontType.MESSAGE.getFont());
        //设置自动换行
        sendArea.setLineWrap(true);
        //设置和换行不断字
        sendArea.setWrapStyleWord(true);
        //设置发送消息文字域的监听事件
        sendArea.addKeyListener(new SendArea(sendArea));
        //构造上下分割面板
        JScrollPane receivePanl = new JScrollPane(receiveArea);
        JScrollPane sendPanl = new JScrollPane(sendArea);
        JSplitPane jSplitPaneRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT,receivePanl,sendPanl);
        jSplitPaneRight.setDividerSize(10);
        jSplitPaneRight.setDividerLocation(500);
        //总体分割布局
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelLeft,jSplitPaneRight);
        jSplitPane.setDividerSize(10);
        jSplitPane.setDividerLocation(160);
        jFrame.setContentPane(jSplitPane);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    /**
     * 展示接收消息
     * @param msg
     */
    public static void showReceiveMsg(String msg){
        receiveArea.append("other:"+msg+"\n");
    }
}
