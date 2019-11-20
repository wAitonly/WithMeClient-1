package com.withme.swing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 接收域表格行数据模型对象
 * @author zhaobenquan 2019/11/19
 */
@Getter
@Setter
@AllArgsConstructor
public class ReceiveAreaTableModel {
    /**
     * 对方头像
     */
    private String left;
    /**
     * 消息体
     */
    private String msg;
    /**
     * 己方头像
     */
    private String right;
}
