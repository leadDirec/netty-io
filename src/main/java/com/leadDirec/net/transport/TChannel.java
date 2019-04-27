package com.leadDirec.net.transport;

import java.net.InetSocketAddress;

public interface TChannel extends Endpoint {

    /**
     * 获取attachment
     *
     * @return the attachment
     */
    Object getAttachment();

    /**
     * 是否授权访问
     *
     * @return
     */
    boolean isAuthorize();

    /**
     * 设置访问授权
     *
     * @param isAuthorize
     */
    void setAuthorize(boolean isAuthorize);

    /**
     * 设置IO通信协议密钥
     *
     * @param secretKeyBytes
     */
    void setSecretKeyBytes(byte[] secretKeyBytes);

    /**
     * 获取IO通信协议密钥
     *
     * @return secretKeyBytes
     */
    byte[] getSecretKeyBytes();

    /**
     * 设置attachment
     *
     * @param attachment
     *            the attachment to set
     */
    void setAttachment(Object attachment);

    /**
     * 获取真实通道
     *
     * @return
     */
    Object getChannel();

    /**
     * 是否连接
     *
     * @return
     */
    boolean isConnected();

    /**
     * 获取LocalAddress
     *
     * @return
     */
    public InetSocketAddress getLocalAddress();

    /**
     * 获取RemoteAddress
     *
     * @return
     */
    public InetSocketAddress getRemoteAddress();

    /**
     * 发送消息
     *
     * @param message
     */
    void send(Object message);

    /**
     * 发送并且立刻刷新
     *
     * @param message
     */
    void sendAndflush(Object message);

    /**
     * 销毁
     */
    void destroy();

    /**
     * 刷新
     */
    void flush();

    /**
     * 关闭通道
     */
    void close();

}
