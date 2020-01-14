package com.example.test;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/** 
* @date 2020-01-03 16:56:53
* @author LEI
* TODO
*/

public class TimeDecode extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes()<4) {
			return;
		}
		out.add(new UnixTime(in.readUnsignedInt()));
		
	}

}
