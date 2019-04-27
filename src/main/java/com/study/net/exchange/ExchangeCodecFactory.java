package com.study.net.exchange;

import com.study.net.io.buffer.TByteBuffer;
import com.study.net.transport.CodecFactory;

import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author xiangdao
 */
public class ExchangeCodecFactory implements CodecFactory<TByteBuffer,TByteBuffer> {

    /**
     * 消息编码解码工厂
     */
    private CodecFactory<ObjectOutput, ObjectInput> codecFactory;

    /**
     * 构造方法
     *
     * @param codecFactory
     */
    public ExchangeCodecFactory(CodecFactory<ObjectOutput, ObjectInput> codecFactory) {
        this.codecFactory = codecFactory;
    }

    @Override
    public Encoder<TByteBuffer> getEncoder() {
        return new HeaderEncoder(codecFactory.getEncoder());
    }

    @Override
    public Decoder<TByteBuffer> getDecoder() {
        return new HeaderDecoder(codecFactory.getDecoder());
    }
}
