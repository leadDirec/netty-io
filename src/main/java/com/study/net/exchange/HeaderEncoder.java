package com.study.net.exchange;

import com.study.net.io.buffer.TByteBuffer;
import com.study.net.io.buffer.TByteBufferAllocator;
import com.study.net.transport.CodecFactory;
import com.study.net.transport.TChannel;

import java.io.ObjectOutput;

/**
 * @author xiangdao
 */
public class HeaderEncoder implements CodecFactory.Encoder<TByteBuffer> {

    /**
     * 消息编码器
     */
    private CodecFactory.Encoder<ObjectOutput> msgEncoder;

    /**
     * ObjectOutput适配器
     */
    private ObjectOutputAdapter objectOutputAdapter = new ObjectOutputAdapter();

    /**
     * 临时buffer TODO 需要优化 利用ThreadLocal
     */
    private TByteBuffer tempBuffer = null;

    /**
     * 构造方法
     *
     * @param msgEncoder
     */
    public HeaderEncoder(CodecFactory.Encoder<ObjectOutput> msgEncoder) {
        this.msgEncoder = msgEncoder;
    }

    /*
     *
     */
    public void encode(TChannel channel, TByteBuffer buffer, Object message) throws Exception {
        try {//  TODO 增加字节流加密 压缩 处理算法接口
            // 临时buffer TODO 需要优化 利用ThreadLocal
            tempBuffer = TByteBufferAllocator.heapBuffer(128);
            objectOutputAdapter.resetChannelByteBuffer(tempBuffer);
            msgEncoder.encode(channel, objectOutputAdapter, message);
            int messageByteSize = tempBuffer.readableBytes();
            buffer.writeInt(messageByteSize + 11);
            buffer.writeByte(0);
            buffer.writeLong(System.currentTimeMillis());
            buffer.writeByte(0);
            buffer.writeByte(0);
            buffer.writeBytes(tempBuffer);
        } finally {
            objectOutputAdapter.resetChannelByteBuffer(null);
            if (tempBuffer != null) {
                tempBuffer.release();
                tempBuffer = null;
            }
        }
    }



}
