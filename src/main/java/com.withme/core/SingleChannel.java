package com.withme.core;


import io.netty.channel.Channel;

/**
 * 客户端连接通道
 * @author zhaobenquan
 */
public class SingleChannel {

    /**
     * 通道
     */
    public Channel channel;

    private volatile static SingleChannel singleChannel;

    /**
     * 构造方法
     * @param channel
     */
    private SingleChannel(Channel channel){
        this.channel = channel;
    }

    /**
     * 单例模式，双重否定检查
     * @param channel
     * @return
     */
    public static SingleChannel initSingleChannel(Channel channel){
        if(null == singleChannel){
            synchronized (SingleChannel.class){
                if(null == singleChannel){
                    singleChannel = new SingleChannel(channel);
                }
            }
        }
        return singleChannel;
    }

    public static SingleChannel getInstance(){
        return singleChannel;
    }
}
