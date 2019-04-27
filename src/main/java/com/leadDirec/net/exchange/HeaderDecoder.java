package com.leadDirec.net.exchange;

import com.leadDirec.net.io.buffer.TByteBuffer;
import com.leadDirec.net.transport.CodecFactory;
import com.leadDirec.net.transport.TChannel;

import java.io.ObjectInput;

/**
 * @author xiangdao 
 */
public class HeaderDecoder implements CodecFactory.Decoder<TByteBuffer> {

    /**
     * 消息解码器
     */
    private CodecFactory.Decoder<ObjectInput> msgDecoder;

    /**
     * ObjectInput适配器
     */
    private ObjectInputAdapter objectInputAdapter = new ObjectInputAdapter();

    public HeaderDecoder(CodecFactory.Decoder<ObjectInput> decoder) {
        this.msgDecoder = decoder;
    }


    @Override
    public Object decode(TChannel channel, TByteBuffer buffer) throws Exception {
        try {//  TODO 增加字节流解密 解压缩 处理算法接口
            buffer.readInt();
            buffer.readByte();
            buffer.readLong();
            buffer.readByte();
            buffer.readByte();
            objectInputAdapter.resetChannelByteBuffer(buffer);
            return msgDecoder.decode(channel, objectInputAdapter);
        } finally {
            objectInputAdapter.resetChannelByteBuffer(null);
        }
    }

}
