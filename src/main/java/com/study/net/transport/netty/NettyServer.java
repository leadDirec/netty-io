package com.study.net.transport.netty;

import com.study.net.config.NetConfig;
import com.study.net.io.buffer.TByteBuffer;
import com.study.net.transport.CodecFactory;
import com.study.net.transport.TChannelHandler;
import com.study.net.transport.TServer;
import com.study.net.util.NamedThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiangdao
 */
public class NettyServer implements TServer {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    /**
     * 配置
     */
    private final NetConfig netConfig;

    /**
     * netty服务
     */
    private ServerBootstrap serverBootstrap;

    /**
     * 服务通道
     */
    private Channel serverChannel;

    /**
     * 编码器工厂
     */
    private CodecFactory<TByteBuffer, TByteBuffer> codecFactory;

    /**
     * 处理器
     */
    private TChannelHandler channelHandler;

    /**
     * 空闲时间
     */
    private int allIdleTimeSeconds;

    /**
     * 构造方法
     *
     * @param netConfig
     */
    public NettyServer(NetConfig netConfig) {
        this.netConfig = netConfig;
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
    public void setCodecFactory(CodecFactory<TByteBuffer, TByteBuffer> codecFactory) {
        this.codecFactory = codecFactory;
    }


    @Override
    public void bind() {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try {
            bossGroup = new NioEventLoopGroup(netConfig.getParameterInt(NetConfig.KEY_BOSS_THREADS),
                    new NamedThreadFactory("TNettyBossGroup", true));
            workerGroup = new NioEventLoopGroup(netConfig.getParameterInt(NetConfig.KEY_WORK_THREADS),
                    new NamedThreadFactory("TNettyWorkerGroup", true));
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_REUSEADDR, true);
            // TODO
            // serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
            int soBacklog = netConfig.getParameterInt(NetConfig.KEY_SO_BACKLOG, NetConfig.DEFAULT_SO_BACKLOG);
            if (soBacklog < NetUtil.SOMAXCONN) {
                soBacklog = NetUtil.SOMAXCONN;
            }
            serverBootstrap.option(ChannelOption.SO_BACKLOG, soBacklog);
            //            serverBootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    doInitChannel(ch);
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(netConfig.getHost(), netConfig.getPort()).sync();
            serverChannel = channelFuture.channel();
            allIdleTimeSeconds = netConfig.getParameterInt(NetConfig.KEY_IDLE_TIMEOUT, NetConfig.DEFAULT_IDLE_TIMEOUT);
            if (allIdleTimeSeconds < NetConfig.DEFAULT_IDLE_TIMEOUT) {
                allIdleTimeSeconds = NetConfig.DEFAULT_IDLE_TIMEOUT;
            }
            String msg = "netty服务绑定成功,host:" + netConfig.getHost() + " port:" + netConfig.getPort() + " IdleTimeout:"
                    + allIdleTimeSeconds;
            LOGGER.info(msg);
        }catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            if (serverChannel != null) {
                serverChannel.close();
            }
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
            throw new IllegalArgumentException(e);
        }
    }

    private void doInitChannel(SocketChannel ch) {
        NettyHandler nettyHandler = new NettyHandler(channelHandler);
        ch.pipeline().addLast(new NettyDecoder(codecFactory.getDecoder()));
        ch.pipeline().addLast(new NettyEncoder(codecFactory.getEncoder()));
        for (ChannelHandler channelHandler : nettyHandler.getChannelHandlers()) {
            ch.pipeline().addLast(channelHandler.getClass().getSimpleName(), channelHandler);
        }
        ch.pipeline().addLast("idlehandler", new IdleStateHandler(0, 0, allIdleTimeSeconds) {
            protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
                Channel channel = ctx.channel();
                NettyChannel nettyChannel = NettyChannel.getChannel(channel);
                if (nettyChannel == null) {
                    LOGGER.warn("channel ip:" + channel.remoteAddress() + " 空闲超时被踢出");
                } else {
                    Object attachment = nettyChannel.getAttachment();
                    if (attachment == null) {
                        LOGGER.warn("channel ip:" + channel.remoteAddress() + " 空闲超时被踢出");
                    } else {
                        LOGGER.warn("channel ip:" + channel.remoteAddress() + ",client:" + attachment.toString()
                                + " 空闲超时被踢出");
                    }
                }
                channel.close();
            }
        });
    }

    @Override
    public void destroy() {
        if (serverChannel != null) {
            serverChannel.close();
        }
        if (serverBootstrap != null) {
            serverBootstrap.group().shutdownGracefully();
            serverBootstrap.childGroup().shutdownGracefully();
        }
    }
}
