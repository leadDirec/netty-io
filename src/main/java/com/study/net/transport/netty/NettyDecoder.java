package com.study.net.transport.netty;

import com.study.net.exception.TTCodecException;
import com.study.net.io.buffer.TByteBuffer;
import com.study.net.transport.CodecFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 *  netty解码器
 * @author xiangdao
 */
public class NettyDecoder extends ByteToMessageDecoder {

    /**
     * 协议体长度标记所占字节数
     */
    private static final int MSG_BODY_LEN_MARK_BYTE_SIZE = 4;

    /**
     * 可接受最大字节数  TODO 应该可配置
     */
    private static final int MAX_BYTE_LEN = 3145728; // 5242880

    /**
     * 空buffer
     */
    private static final ByteBuf EMPTY_BUFFER = UnpooledByteBufAllocator.DEFAULT.heapBuffer(0);

    /**
     * 未读完的buffer,默认为空
     */
    private ByteBuf unreadByteBuf = EMPTY_BUFFER;

    /**
     * decoder使用的TByteBufferAdapter
     */
    public NettyByteBufAdapter nettyByteBufAdapter = new NettyByteBufAdapter(null);

    /**
     * 协议消息解码器
     */
    private CodecFactory.Decoder<TByteBuffer> decoder;

    /**
     * 构造方法
     *
     * @param decoder
     */
    public NettyDecoder(CodecFactory.Decoder<TByteBuffer> decoder) {
        this.decoder = decoder;
    }

    /*
     * (non-Javadoc)
     *
     * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.
     * ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        try {
            decode0(ctx, in, out);
        } catch (Exception e) {
            if (e instanceof TTCodecException) {
                throw e;
            }
            throw new TTCodecException(e);
        }
    }

    /**
     * 解码
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    private void decode0(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (!in.isReadable()) {
            return;
        }
        NettyChannel nettyChannel = NettyChannel.getOrAddChannel(ctx.channel());
        ByteBuf protocolBuffer = unreadByteBuf;
        if (protocolBuffer == EMPTY_BUFFER) {
            protocolBuffer = in;
        } else {
            protocolBuffer.writeBytes(in, in.readerIndex(), in.readableBytes());
            in.readerIndex(in.writerIndex());
        }
        try {
            do {
                if (protocolBuffer.readableBytes() < MSG_BODY_LEN_MARK_BYTE_SIZE) {
                    break;
                }
                int msgLen = protocolBuffer.getInt(protocolBuffer.readerIndex());
                if (msgLen > MAX_BYTE_LEN) {
                    throw new TTCodecException("单个数据包大小,超过单个数据包最大可接受字节数:" + MAX_BYTE_LEN);
                }
                if (msgLen > protocolBuffer.readableBytes() - MSG_BODY_LEN_MARK_BYTE_SIZE) { //防止读半包
                    break;
                }
                out.add(decoder.decode(nettyChannel, nettyByteBufAdapter.resetByteBuf(protocolBuffer)));
            } while (protocolBuffer.isReadable());
        } finally {
            nettyByteBufAdapter.resetByteBuf(null);
            if (protocolBuffer.isReadable()) {
                if (protocolBuffer == in) { //netty自动管理 释放
                    // TODO 需要优化  ByteBuf的initialCapacity可以设置为 剩余长度+protocolBuffer.readableBytes()
                    unreadByteBuf = ByteBufAllocator.DEFAULT.heapBuffer(protocolBuffer.readableBytes());
                    protocolBuffer.readBytes(unreadByteBuf, protocolBuffer.readableBytes());
                } else { //！= in 需手动释放
                    // TODO 需要优化 无需每次都创建新的ByteBuf
                    unreadByteBuf = ByteBufAllocator.DEFAULT.heapBuffer(protocolBuffer.readableBytes());
                    protocolBuffer.readBytes(unreadByteBuf, protocolBuffer.readableBytes());
                    protocolBuffer.release();
                }
            } else {
                if (unreadByteBuf != EMPTY_BUFFER) {
                    if (unreadByteBuf != in) {
                        unreadByteBuf.release();
                    }
                    unreadByteBuf = EMPTY_BUFFER;
                }
            }
            // NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }
}
