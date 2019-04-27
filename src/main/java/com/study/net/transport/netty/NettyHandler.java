package com.study.net.transport.netty;

import com.study.net.transport.TChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * netty处理器
 *
 * @author xiangdao
 *
 */
public class NettyHandler {

    private final TChannelHandler channelHandler;

    private List<ChannelHandler> channelHandlers = new ArrayList<ChannelHandler>(3);

    public NettyHandler(TChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        channelHandlers.add(new ChannelHandler0());
        channelHandlers.add(new NettyInHandler());
        channelHandlers.add(new NettyOutHandler());

        // ReadTimeoutHandler
        // WriteTimeoutHandler
        // IdleStateHandler
    }

    List<ChannelHandler> getChannelHandlers() {
        return channelHandlers;
    }

    public class ChannelHandler0 implements ChannelHandler {

        /*
         * (non-Javadoc)
         *
         * @see io.netty.channel.ChannelHandler#handlerAdded(io.netty.channel.
         * ChannelHandlerContext)
         */
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel());
            try {
                channelHandler.connected(channel);
            } finally {
//                NettyChannel.removeChannelIfDisconnected(ctx.channel());
            }
        }

        /*
         * (non-Javadoc)
         *
         * @see io.netty.channel.ChannelHandler#handlerRemoved(io.netty.channel.
         * ChannelHandlerContext)
         */
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel());
            try {
                channelHandler.disconnected(channel);
            } finally {
                NettyChannel.removeChannelIfDisconnected(ctx.channel());
            }
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * io.netty.channel.ChannelHandler#exceptionCaught(io.netty.channel.
         * ChannelHandlerContext, java.lang.Throwable)
         */
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel());
            try {
                channelHandler.exceptionCaught(channel, cause);
            } finally {
//                NettyChannel.removeChannelIfDisconnected(ctx.channel());
            }
        }
    }

    private class NettyInHandler extends SimpleChannelInboundHandler<Object> {
        private NettyInHandler() {
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel());
            try {
                channelHandler.received(channel, msg);
            } finally {
//                NettyChannel.removeChannelIfDisconnected(ctx.channel());
            }
        }
    }

    private class NettyOutHandler extends ChannelOutboundHandlerAdapter {
        private NettyOutHandler() {
        }

        // @Override
        // public void write(ChannelHandlerContext ctx, Object msg,
        // ChannelPromise promise) throws Exception {
        // super.write(ctx, msg, promise);
        // System.err.println("SimpleChannelInboundHandler的write");
        // }

        // @Override
        // public void flush(ChannelHandlerContext ctx) throws Exception {
        // super.flush(ctx);
        // System.err.println("SimpleChannelInboundHandler的flush");
        // NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel());
        // try {
        // channelHandler.sent(channel, msg);
        // } finally {
        // NettyChannel.removeChannelIfDisconnected(ctx.channel());
        // }
        // }

        // @Override
        // public void disconnect(ChannelHandlerContext ctx, ChannelPromise
        // promise) throws Exception {
        // super.disconnect(ctx, promise);
        // System.err.println("SimpleChannelInboundHandler的disconnect");
        // }
    }


}
