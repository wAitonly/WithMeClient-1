package com.withme.swing;

import lombok.Getter;

import java.awt.*;

/**
 * 字体枚举
 */
@Getter
public enum FontType {
    /**
     * 导航栏字体
     */
    NAVIGATION("宋体", Font.BOLD,20),
    /**
     * 聊天消息字体
     */
    MESSAGE("宋体",Font.ITALIC,20);

    /**
     * 字体
     */
    private Font font;

    /**
     * 构造
     * @param name
     * @param style
     * @param size
     */
    FontType(String name, int style, int size){
        this.font = new Font(name,style,size);
    }

}
