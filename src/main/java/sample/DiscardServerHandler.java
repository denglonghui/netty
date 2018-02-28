package sample;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	private Logger log=Logger.getLogger(DiscardServerHandler.class);
	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		 ByteBuf in = (ByteBuf) msg;
		    try {
		    	String s=in.toString(io.netty.util.CharsetUtil.US_ASCII);
		    	 log.info(s);
		    	  System.out.println(s);
		    } finally {
		        ReferenceCountUtil.release(msg); // (2)
		    }

	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
	        // Close the connection when an exception is raised.
	        cause.printStackTrace();
	        ctx.close();
	    }

}
