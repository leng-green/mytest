package com.abtc.netty.server;

import com.abtc.netty.handler.FileServerHandle;
import com.abtc.netty.server.initializer.FileChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component//交给spring管理
public class NettyFileServer {

    @Value("${netty.port}")
    private int port;

    private EventLoopGroup group = new NioEventLoopGroup();

    private ServerBootstrap serverBootStrap = new ServerBootstrap();

    public void start() throws InterruptedException {

        try{
            serverBootStrap
                    .group(group)/*将线程组传入*/
                    .channel(NioServerSocketChannel.class)//指定使用NIO进行网络传输
                    .localAddress(new InetSocketAddress(port))//指定服务器监听端口
                    .childHandler(new FileChannelInitializer());

            //异步绑定到服务器，sync()会阻塞直到完成
            ChannelFuture f = serverBootStrap.bind().sync();
            System.out.println("Netty server  start,port is " + port);
            //阻塞直到服务器的channel关闭
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();/*优雅关闭线程组*/
            System.out.println("关闭server");
        }

    }

}
