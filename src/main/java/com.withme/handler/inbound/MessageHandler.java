package com.withme.handler.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息处理handler
 * @author zhaobenquan
 */
public class MessageHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "{\"content\":\"hello\",\"topic\":\"msg_a\"}";
        ctx.channel().writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info((String) msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
