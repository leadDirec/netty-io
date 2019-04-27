package com.leadDirec.net.transport.netty;

import com.leadDirec.net.io.buffer.netty.NettyByteBuf;
import io.netty.buffer.ByteBuf;

public class NettyByteBufAdapter extends NettyByteBuf {
    /**
     * 构造方法
     *
     * @param byteBuf
     */
    public NettyByteBufAdapter(ByteBuf byteBuf) {
        super(byteBuf);
    }

    /**
     * 重置
     *
     * @param byteBuf
     * @return
     */
    NettyByteBufAdapter resetByteBuf(ByteBuf byteBuf) {
        super.byteBuf = byteBuf;
        return this;
    }
}
