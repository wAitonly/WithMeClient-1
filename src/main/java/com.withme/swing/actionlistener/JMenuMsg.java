package com.withme.swing.actionlistener;

import com.withme.swing.FontType;
import com.withme.swing.ReceiveAreaTable;
import com.withme.swing.ReceiveAreaTableModel;
import com.withme.swing.ReceiveAreaTableRenderer;
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
     * 接收消息表格
     */
    public static JTable receiveArea;
    /**
     * 接收消息数据table
     */
    public static ReceiveAreaTable table;

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
        JLabel jLabel = new JLabel("尚未开发");
        jLabel.setFont(new Font("宋体",Font.BOLD,15));
        panelLeft.add(jLabel);
        panelLeft.setLayout(new FlowLayout());
        //右边消息框分发送和接收消息两部分
        //消息接收域为表格
        receiveArea = new JTable();
        //设置聊天字体
        receiveArea.setTableHeader(null);
        table = new ReceiveAreaTable();
        receiveArea.setModel(table);
        //支持自定义头像
        ReceiveAreaTableRenderer renderer = new ReceiveAreaTableRenderer("style_img/head_left.PNG","style_img/head_right.PNG");
        //最左侧列设置渲染器
        receiveArea.setRowHeight(60);
        receiveArea.getColumnModel().getColumn(0).setCellRenderer(renderer);
        receiveArea.getColumnModel().getColumn(1).setCellRenderer(renderer);
        receiveArea.getColumnModel().getColumn(2).setCellRenderer(renderer);
        //消息发送域为文本域
        JTextArea sendArea = new JTextArea();
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
        jSplitPaneRight.setDividerLocation(400);
        //总体分割布局
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelLeft,jSplitPaneRight);
        jSplitPane.setDividerSize(10);
        jSplitPane.setDividerLocation(80);
        jFrame.setContentPane(jSplitPane);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    /**
     * 展示接收消息
     * @param msg
     */
    public static void showReceiveMsg(String msg){
        table.addModel(new ReceiveAreaTableModel("1",msg,""));
    }
}
