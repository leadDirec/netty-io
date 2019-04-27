package com.study.net.transport.netty;

import com.study.net.transport.TChannel;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * netty实现的io通道
 * @author xiangdao
 */
public class NettyChannel implements TChannel{

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyChannel.class);

    private static final AttributeKey<NettyChannel> NETTYCHANNEL_ATTR = AttributeKey.valueOf("NETTYCHANNEL_ATTR");

    private static final AttributeKey<Object> CLIENT_SESSION_ATTR = AttributeKey.valueOf("CLIENT_SESSION_ATTR");


    /**
     * 空bytes
     */
    private static final byte[] EMPTY_BYTES = new byte[0];

    /**
     * netty channel
     */
    private final Channel channel;

    /**
     * 设置自定义ID
     */
    private String uid;

    // /**
    // * 附件
    // */
    // private Object attachment;

    /**
     * 是否授权访问
     */
    private boolean isAuthorize;

    /**
     * IO通信协议密钥
     */
    private byte[] secretKeyBytes;

    /**
     * hashcode
     */
    private final int hashCode;

    /**
     * 获取uid
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置uid
     *
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 构造方法
     *
     * @param channel
     */
    private NettyChannel(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("netty channel == null;");
        }
        this.channel = channel;
        this.hashCode = 31 + channel.hashCode();
    }

    /**
     * 获取isAuthorize
     *
     * @return the isAuthorize
     */
    public boolean isAuthorize() {
        return isAuthorize;
    }

    /**
     * 设置isAuthorize
     *
     * @param isAuthorize
     *            the isAuthorize to set
     */
    public void setAuthorize(boolean isAuthorize) {
        this.isAuthorize = isAuthorize;
    }

    /**
     * 获取LocalAddress
     *
     * @return
     */
    public InetSocketAddress getLocalAddress() {
        return (InetSocketAddress) channel.localAddress();
    }

    /**
     * 获取RemoteAddress
     *
     * @return
     */
    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) channel.remoteAddress();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.tt.net.transport.TChannel#setSecretKeyBytes(byte[])
     */
    @Override
    public void setSecretKeyBytes(byte[] secretKeyBytes) {
        this.secretKeyBytes = secretKeyBytes;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.tt.net.transport.TChannel#getSecretKeyBytes()
     */
    @Override
    public byte[] getSecretKeyBytes() {
        byte[] ret = secretKeyBytes;
        if (ret == null) {
            return EMPTY_BYTES;
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.tt.net.transport.TChannel#getChannel()
     */
    public Object getChannel() {
        return channel;
    }

    /*
     * (non-Javadoc)
     *
     */
    public Object getAttachment() {
        return channel.attr(CLIENT_SESSION_ATTR).get();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     */
    public void setAttachment(Object attachment) {
        if (attachment != null) {
            channel.attr(CLIENT_SESSION_ATTR).set(attachment);
        } else {
            channel.attr(CLIENT_SESSION_ATTR).remove();
        }
    }

    public boolean isConnected() {
        return channel.isActive();
    }

    public void close() {
        try {
            channel.close();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
//        try {
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("Close netty channel " + channel);
//            }
//        } catch (Exception e) {
//            LOGGER.warn(e.getMessage(), e);
//        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void send(Object message) {
        this.channel.write(message);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     */
    public void sendAndflush(Object message) {
        this.channel.writeAndFlush(message);
    }

    /*
     * (non-Javadoc)
     *
     */
    public void flush() {
        this.channel.flush();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (null == other) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        return channel.equals(((NettyChannel) other).channel);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return "NettyChannel [channel=" + channel + "]";
    }

    /*
     * (non-Javadoc)
     *
     */
    @Override
    public void destroy() {
        channel.attr(NETTYCHANNEL_ATTR).remove();
        channel.attr(CLIENT_SESSION_ATTR).remove();
    }


    static NettyChannel getChannel(Channel ch) {
        if (ch == null) {
            return null;
        }
        Attribute<NettyChannel> attribute = ch.attr(NETTYCHANNEL_ATTR);
        if (attribute == null) {
            return null;
        }
        return attribute.get();
    }

    static NettyChannel getOrAddChannel(Channel ch) {
        if (ch == null) {
            return null;
        }
        Attribute<NettyChannel> attribute = ch.attr(NETTYCHANNEL_ATTR);
        NettyChannel ret = attribute.get();
        if (ret == null) {
            NettyChannel nc = new NettyChannel(ch);
            if (ch.isActive()) {
                ret = attribute.setIfAbsent(nc);
            }
            if (ret == null) {
                ret = nc;
            }
        }
        return ret;
    }

    static void removeChannelIfDisconnected(Channel ch) {
        if (ch != null && !ch.isActive()) {
            NettyChannel nettyChannel = NettyChannel.getChannel(ch);
            if (nettyChannel == null) {
                LOGGER.warn("channel ip:" + ch.remoteAddress() + " removeChannelIfDisconnected");
            } else {
                Object attachment = nettyChannel.getAttachment();
                if (attachment == null) {
                    LOGGER.warn("channel ip:" + ch.remoteAddress() + " removeChannelIfDisconnected");
                } else {
                    LOGGER.warn("channel ip:" + ch.remoteAddress() + ",client:" + attachment.toString()
                            + " removeChannelIfDisconnected");
                }
            }
            ch.attr(NETTYCHANNEL_ATTR).remove();
        }
    }
}
