package com.example.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/** 
* @date 2019-10-14 15:19:14
* @author LEI
* Echo服务器
*/

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		ctx.write(msg);
		ctx.flush();
    }

}
