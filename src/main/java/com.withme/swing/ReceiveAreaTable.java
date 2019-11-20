package com.withme.swing;

import com.withme.swing.actionlistener.JMenuMsg;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息接收域表格
 * @author zhaobenquan 2019/11/19
 */
public class ReceiveAreaTable extends AbstractTableModel {
    /**
     * 表格数据
     */
    private List<ReceiveAreaTableModel> dataList;

    /**
     * 添加表格数据
     * @param model
     * @return
     */
    public synchronized ReceiveAreaTable addModel(ReceiveAreaTableModel model){
        if(this.dataList == null){
            this.dataList = new ArrayList<>();
        }
        dataList.add(model);
        int len = dataList.size();
        //通知监听器已插入一行，需要重新渲染
        this.fireTableRowsInserted(len - 1, len - 1);
        //设置滚动条自定滚下
        int rowCount = JMenuMsg.receiveArea.getRowCount();
        JMenuMsg.receiveArea.getSelectionModel().setSelectionInterval(rowCount-1, rowCount-1);
        Rectangle rect = JMenuMsg.receiveArea.getCellRect(rowCount-1, 0, true);
        JMenuMsg.receiveArea.scrollRectToVisible(rect);
        return this;
    }

    /**
     * 获取行的数目
     * @return
     */
    @Override
    public int getRowCount() {
        if(null != this.dataList){
            return dataList.size();
        }else {
            return 0;
        }
    }

    /**
     * 获取列的数目
     * @return
     */
    @Override
    public int getColumnCount() {
        return 3;
    }

    /**
     * 获取单元格的值
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //直接返回整行对象，单元格渲染器中取到整个行数据对象
        return dataList.get(rowIndex);
    }
}
