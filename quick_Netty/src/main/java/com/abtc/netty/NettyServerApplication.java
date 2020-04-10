package com.abtc.netty;

import com.abtc.netty.server.NettyFileServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyServerApplication implements CommandLineRunner {


    @Autowired
    private NettyFileServer nettyFileServer;


    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("服务器即将启动");
        nettyFileServer.start();
        System.out.println("服务器关闭");
    }
}
