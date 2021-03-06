package com.withme.core;

import com.withme.handler.inbound.MessageHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * 管道初始化
 * @author zhaobbenquan 2019/10/15
 */
public class Initializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel){
        nioSocketChannel.pipeline()
                .addLast(new StringEncoder(Charset.forName("UTF-8")))
                .addLast(new StringDecoder(Charset.forName("UTF-8")))
                .addLast(new MessageHandler());
    }
}
