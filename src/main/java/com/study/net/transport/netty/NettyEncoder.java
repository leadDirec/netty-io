package com.study.net.transport.netty;

import com.study.net.exception.TTCodecException;
import com.study.net.io.buffer.TByteBuffer;
import com.study.net.transport.CodecFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * netty编码器
 *
 * @author xiangdao
 *
 */
public class NettyEncoder extends MessageToByteEncoder<Object> {

    /**
     * 协议体长度标记所占字节数
     */
    private static final int MSG_BODY_LEN_MARK_BYTE_SIZE = 4;

    /**
     * decoder使用的TByteBufferAdapter
     */
    private NettyByteBufAdapter nettyByteBufAdapter = new NettyByteBufAdapter(null);

    /**
     * 协议消息编码器
     */
    private final CodecFactory.Encoder<TByteBuffer> encoder;

    /**
     * 构造方法
     *
     * @param encoder
     */
    public NettyEncoder(CodecFactory.Encoder<TByteBuffer> encoder) {
        this.encoder = encoder;
    }

    /*
     * (non-Javadoc)
     *
     * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.
     * ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        try {
            encode0(ctx, msg, out);
        } catch (Exception e) {
            if (e instanceof TTCodecException) {
                throw e;
            }
            throw new TTCodecException(e);
        }
    }

    /**
     * 编码
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    private void encode0(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        NettyChannel nettyChannel = NettyChannel.getOrAddChannel(ctx.channel());
        try {
            encoder.encode(nettyChannel, nettyByteBufAdapter.resetByteBuf(out), msg);
        } finally {
            nettyByteBufAdapter.resetByteBuf(null);
//            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

}
