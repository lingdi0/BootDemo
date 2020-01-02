package com.example.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/** 
* @date 2019-10-14 15:19:14
* @author LEI
* 丢失服务器
*/

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		ByteBuf in = (ByteBuf) msg;
	    try {
	        while (in.isReadable()) {
	            System.out.print((char) in.readByte());
	            System.out.flush();
	        }
	        ctx.writeAndFlush("123");
	    }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
