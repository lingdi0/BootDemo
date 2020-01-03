package com.example.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/** 
* @date 2019-10-14 15:16:38
* @author LEI
* TODO
*/

public class DiscardServer {
	
	private int port;
    
    public DiscardServer(int port) {
        this.port = port;
    }
    
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 处理I/O操作的多线程时间循环
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // 设置服务器的帮助程序类
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // 
             .childHandler(new ChannelInitializer<SocketChannel>() { // 
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new TimeEncoder(),new DiscardServerHandler(),new TimeServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)          // 等待超时
             .childOption(ChannelOption.SO_KEEPALIVE, true); // 保持连接
    
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // 绑定端口
    
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }
}
