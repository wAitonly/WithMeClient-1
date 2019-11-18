package com.withme.swing.actionlistener;

import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 导航栏我的好友监听事件
 * @author zhaobenquan 2019/11/18
 */
@AllArgsConstructor
public class JMenuFriend implements MouseListener {
    /**
     * 基础面板
     */
    private JFrame jFrame;
    /**
     * 鼠标点击事件
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        show();
    }

    /**
     * 鼠标按下事件
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * 鼠标抬起事件
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * 鼠标滑入事件
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * 鼠标划出事件
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * 展示我的消息面板
     */
    public void show(){
        //我的好友子面板
        JPanel panel = new JPanel();
        JLabel jLabel = new JLabel("尚未开发！");
        jLabel.setFont(new Font("宋体",Font.BOLD,60));
        panel.add(jLabel);
        jFrame.setContentPane(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
