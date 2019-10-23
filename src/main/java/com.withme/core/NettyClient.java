package com.withme.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Optional;

/**
 * Netty客户端
 * @author zhaobenquan 2019/10/15
 */
@Component
public class NettyClient {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    /**
     * 服务端参数
     */
    private final String serverAddress = "127.0.0.1" ;
    private final Integer serverPort = 8080 ;

    /**
     * 线程池组
     */
    private NioEventLoopGroup loopGroup = new NioEventLoopGroup();

    /**
     * 客户端启动
     */
    @PostConstruct
    public void clientStart() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        //初始化启动器，绑定线程池组
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new Initializer());
        //绑定服务器等待直到绑定完成,sync()方法会阻塞直到服务器完成绑定,然后服务器等待通道关闭
        Optional<ChannelFuture> channelFuture = Optional.ofNullable(bootstrap.connect(serverAddress, serverPort).sync());
        channelFuture.map(a -> a.isSuccess()?"客户端启动成功":"客户端启动失败")
                .ifPresent(logger::info);
    }
}
