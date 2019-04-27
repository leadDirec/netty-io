package com.study.net.transport;

/**
 * 编码解码器工厂
 *
 * @author xiangdao
 *
 */
public interface CodecFactory<O,I> {

    /**
     * 获取编码器
     *
     * @return MessageEncoder
     */
    Encoder<O> getEncoder();

    /**
     * 获取解码器
     *
     * @return MessageDecoder
     */
    Decoder<I> getDecoder();

    /**
     * 协议消息编码器
     *
     * @author xiangdao
     *
     */
    public interface Encoder<O> {

        /**
         * 编码
         *
         * @param channel
         * @param o
         * @param message
         * @throws IOException
         */
        void encode(TChannel channel, O o, Object message) throws Exception;

    }

    /**
     * 解码器
     *
     * @author xiangdao
     *
     */
    public interface Decoder<I> {

        /**
         * 解码
         *
         * @param channel
         * @param i
         * @return msg
         * @throws IOException
         */
        Object decode(TChannel channel, I i) throws Exception;

    }

}
