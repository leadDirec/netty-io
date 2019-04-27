package com.study.net.io.buffer;

import com.study.net.io.buffer.netty.NettyByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * TByteBuffer分配器
 * @author xiangdao
 */
public class TByteBufferAllocator {

    /**
     * 构造方法
     */
    private TByteBufferAllocator() {
    }

    public static TByteBuffer heapBuffer(int initcapacity) {
        ByteBuf bf = ByteBufAllocator.DEFAULT.heapBuffer(initcapacity);
        // bf = bf.order(ByteOrder.LITTLE_ENDIAN);
        return new NettyByteBuf(bf);
    }

    public static TByteBuffer heapBuffer() {
        ByteBuf bf = ByteBufAllocator.DEFAULT.heapBuffer();
        // bf = bf.order(ByteOrder.LITTLE_ENDIAN);
        return new NettyByteBuf(bf);
    }

}
