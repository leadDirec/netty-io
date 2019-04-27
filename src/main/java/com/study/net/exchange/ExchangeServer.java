package com.study.net.exchange;

import com.study.net.config.NetConfig;
import com.study.net.transport.CodecFactory;
import com.study.net.transport.TChannelHandler;
import com.study.net.transport.TServer;
import com.study.net.transport.netty.NettyServer;

import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author xiangdao
 */
public class ExchangeServer {

    /**
     * io服务
     */
    private TServer ioServer;

    /**
     * 处理器
     */
    private TChannelHandler channelHandler;

    /**
     * 编码器
     */
    private CodecFactory<ObjectOutput, ObjectInput> codecFactory;

    /**
     * 构造方法
     *
     * @param netConfig
     */
    public ExchangeServer(NetConfig netConfig, TChannelHandler channelHandler,
                          CodecFactory<ObjectOutput, ObjectInput> codecFactory) {
        this.channelHandler = channelHandler;
        this.codecFactory = codecFactory;
        // TODO 时间关系,以后通过扩展点方式注入或者工厂等方方式创建ioServer
        NettyServer nettyServer = new NettyServer(netConfig);
        nettyServer.setChannelHandler(channelHandler);
        nettyServer.setCodecFactory(new ExchangeCodecFactory(codecFactory));
        ioServer = nettyServer;
    }

    /**
     * 设置channelHandler
     *
     * @param channelHandler
     *            the channelHandler to set
     */
    public void setChannelHandler(TChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    /**
     * 设置codecFactory
     *
     * @param codecFactory
     *            the codecFactory to set
     */
    public void setCodecFactory(CodecFactory<ObjectOutput, ObjectInput> codecFactory) {
        this.codecFactory = codecFactory;
    }

    /**
     * 获取channelHandler
     *
     * @return the channelHandler
     */
    public TChannelHandler getChannelHandler() {
        return channelHandler;
    }

    /**
     * 获取codecFactory
     *
     * @return the codecFactory
     */
    public CodecFactory<ObjectOutput, ObjectInput> getCodecFactory() {
        return codecFactory;
    }

    /**
     * 绑定
     */
    public void bind() {
        ioServer.bind();
    }

    /**
     * 销毁方法
     */
    public void destroy() {
        ioServer.destroy();
    }


}
