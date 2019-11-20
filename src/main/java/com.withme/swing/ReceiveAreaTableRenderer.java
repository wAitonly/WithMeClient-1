package com.withme.swing;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 消息接收域表格渲染器
 * @author zhaobenquan 2019/11/19
 */
@AllArgsConstructor
public class ReceiveAreaTableRenderer implements TableCellRenderer {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ReceiveAreaTableRenderer.class);
    /**
     * 左侧头像
     */
    private String leftHeadIcon;
    /**
     * 左侧消息背景
     */
    private static String LEFTMSGBACK = "style_img/msgbg_left.PNG";
    /**
     * 右侧头像
     */
    private String rightHeadIcon;
    /**
     * 右侧消息背景
     */
    private static String RIGHTMSGBACK = "style_img/msgbg_right.PNG";
    /**
     * 列下标
     */
    private static final Integer LEFTINDEX = 0;
    private static final Integer MSGINDEX = 1;
    private static final Integer RIGHTINDEX = 2;
    /**
     * 渲染每个单元格
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ReceiveAreaTableModel data = (ReceiveAreaTableModel) value;
        JPanel jPanel = null;
        //左右两侧头像标签
        JLabel jLabel;
        //消息体文字域
        JTextArea jTextArea;
        //重新渲染单元格
        try {
            if(LEFTINDEX == column && !StringUtils.isEmpty(data.getLeft())){
                //左侧头像
                jLabel = new JLabel();
                File imageFile = new ClassPathResource(this.leftHeadIcon).getFile();
                FileInputStream fls = new FileInputStream(imageFile);
                byte[] imageByte = new byte[(int)imageFile.length()];
                fls.read(imageByte);
                fls.close();
                //设置图标
                Icon mHeadLeft= new ImageIcon(imageByte);
                jLabel.setIcon(mHeadLeft);
                jPanel = new JPanel();
                jPanel.add(jLabel);
            }else if(1 == column){
                //中间消息体
                File imageFile;
                if(!StringUtils.isEmpty(data.getLeft())){
                    //左侧
                    imageFile = new ClassPathResource(LEFTMSGBACK).getFile();
                }else {
                    //右侧
                    imageFile = new ClassPathResource(RIGHTMSGBACK).getFile();
                }
                FileInputStream fls = new FileInputStream(imageFile);
                byte[] imageByte = new byte[(int)imageFile.length()];
                fls.read(imageByte);
                fls.close();
                //设置图标
                ImageIcon msg= new ImageIcon(imageByte);
                jTextArea = new JTextArea(){
                    @Override
                    public void paint(Graphics g) {
                        g.drawImage(msg.getImage(), 0, 0, this);
                        super.paint(g);
                    }
                };
                jTextArea.append(data.getMsg());
                jTextArea.setFont(FontType.MESSAGE.getFont());
                //设置透明
                jTextArea.setOpaque(false);
                //设置自动换行
                jTextArea.setLineWrap(true);
                jTextArea.setWrapStyleWord(true);
                jPanel = new JPanel();
                jPanel.add(jTextArea);
                jPanel.setOpaque(false);
            }else if(RIGHTINDEX == column && !StringUtils.isEmpty(data.getRight())){
                //右侧头像
                jLabel = new JLabel();
                File imageFile = new ClassPathResource(this.rightHeadIcon).getFile();
                FileInputStream fls = new FileInputStream(imageFile);
                byte[] imageByte = new byte[(int)imageFile.length()];
                fls.read(imageByte);
                fls.close();
                //设置图标
                Icon mHeadLeft= new ImageIcon(imageByte);
                jLabel.setIcon(mHeadLeft);
                jPanel = new JPanel();
                jPanel.add(jLabel);
            }
        }catch (IOException e){
            logger.error("receive area set table cell style error exception for",e);
        }
        return jPanel;
    }
}
