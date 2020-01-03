package com.example.test;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/** 
* @date 2019-10-14 15:19:14
* @author LEI
* 时间服务器
*/

public class TimeServerHandler extends ChannelInboundHandlerAdapter {


    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
         System.out.println("一个连接");
         ChannelFuture f = ctx.writeAndFlush(new UnixTime()); //
         f.addListener(ChannelFutureListener.CLOSE); //
	}

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { //
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
