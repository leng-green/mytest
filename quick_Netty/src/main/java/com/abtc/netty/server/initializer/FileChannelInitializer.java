package com.abtc.netty.server.initializer;

import com.abtc.netty.handler.FileServerHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class FileChannelInitializer extends ChannelInitializer<SocketChannel> {

    private EventExecutorGroup businessEventExecutorGroup = new DefaultEventExecutorGroup(10);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //先从上往下执行，再从下往上执行
        //http服务器端对response编码
        pipeline.addLast("encoder", new HttpResponseEncoder());
        //http服务器端对request解码3
        pipeline.addLast("decoder", new HttpRequestDecoder());
        //合并请求
        pipeline.addLast("aggregator", new HttpObjectAggregator(655300000));
        //业务逻辑处理
        pipeline.addLast(businessEventExecutorGroup, new FileServerHandle());
    }

}
