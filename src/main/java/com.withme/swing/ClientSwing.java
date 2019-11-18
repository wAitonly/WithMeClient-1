package com.withme.swing;

import com.withme.swing.actionlistener.JMenuFriend;
import com.withme.swing.actionlistener.JMenuMsg;
import javax.swing.*;

/**
 * 可视化客户端
 * @author zhaobenquan 2019/11/18
 */
public class ClientSwing {
    /**
     * 启动客户端
     */
    public static void start(){
        //基础面板
        JFrame jFrame = new JFrame("WithMe");
        jFrame.setSize(800, 800);
        jFrame.setLocation(200, 200);
        //导航栏
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenuMsg = new JMenu("我的消息");
        JMenu jMenuFriend = new JMenu("我的好友");
        jMenuMsg.setFont(FontType.NAVIGATION.getFont());
        jMenuFriend.setFont(FontType.NAVIGATION.getFont());
        jMenuBar.add(jMenuMsg);
        jMenuBar.add(jMenuFriend);
        jFrame.setJMenuBar(jMenuBar);
        //默认展示消息界面
        new JMenuMsg(jFrame).show();
        //绑定导航栏监听事件
        jMenuFriend.addMouseListener(new JMenuFriend(jFrame));
        //绑定导航栏监听事件
        jMenuMsg.addMouseListener(new JMenuMsg(jFrame));
        //基础面板设置
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
