package com.leadDirec.net.transport;

public interface TChannelHandler {

    /**
     * 连接成功
     *
     * @param channel
     */
    void connected(TChannel channel) throws Exception;

    /**
     * 断开连接
     *
     * @param channel
     */
    void disconnected(TChannel channel) throws Exception;

    /**
     * 接收到消息
     *
     * @param channel
     * @param message
     */
    void received(TChannel channel, Object message) throws Exception;

    /**
     * 发生异常
     *
     * @param channel
     * @param exception
     */
    void exceptionCaught(TChannel channel, Throwable exception) throws Exception;

}
