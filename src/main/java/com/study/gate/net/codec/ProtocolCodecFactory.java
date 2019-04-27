package com.study.gate.net.codec;

import com.study.net.transport.CodecFactory;
import com.study.net.transport.TChannel;

import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 协议编码解码器工厂
 *
 * @author xiangdao
 *
 */
public class ProtocolCodecFactory implements CodecFactory<ObjectOutput,ObjectInput> {

    /**
     * 内部实现的编码器
     */
    private static final Encoder<ObjectOutput> INNER_ENCODER = new InnerEncoder();

    /**
     * 内部实现的解码器
     */
    private static final Decoder<ObjectInput> INNER_DECODER = new InnerDecoder();

    @Override
    public Encoder<ObjectOutput> getEncoder() {
        return INNER_ENCODER;
    }

    @Override
    public Decoder<ObjectInput> getDecoder() {
        return INNER_DECODER;
    }

    /**
     * 编码器实现
     *
     * @author xiangdao
     *
     */
    private static class InnerEncoder implements Encoder<ObjectOutput> {

        /*
         * (non-Javadoc)
         *
         * @see
         * .transport.CodecFactory.Encoder#encode(com.jjjr.tt
         * .net.transport.TChannel, java.lang.Object, java.lang.Object)
         */
        public void encode(TChannel channel, ObjectOutput objectOutput, Object message) throws Exception {
            if (message == null) {
                throw new IllegalArgumentException("编码器不支持message == null");
            }
            ImProtocolCodecUtils.encode(message, objectOutput);
        }
    }

    /**
     * 解码器实现
     *
     * @author xiangdao
     *
     */
    private static class InnerDecoder implements Decoder<ObjectInput> {

        /*
         * (non-Javadoc)
         *
         * @see
         * .transport.CodecFactory.Decoder#decode(com.jjjr.tt
         * .net.transport.TChannel, java.lang.Object)
         */
        public Object decode(TChannel channel, ObjectInput objectInput) throws Exception {
            return ImProtocolCodecUtils.decode(objectInput);
        }
    }

}
