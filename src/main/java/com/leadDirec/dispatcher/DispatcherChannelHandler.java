package com.leadDirec.dispatcher;

import com.leadDirec.common.dto.MessageBean;
import com.leadDirec.dispatcher.filter.ChannelFilterChain;
import com.leadDirec.net.transport.TChannel;
import com.leadDirec.net.transport.TChannelHandler;

public class DispatcherChannelHandler implements TChannelHandler {

    /**
     * 过滤链
     */
    private ChannelFilterChain channelFilterChain;

    public DispatcherChannelHandler(ChannelFilterChain channelFilterChain) {
        this.channelFilterChain = channelFilterChain;
    }

    @Override
    public void connected(TChannel channel) throws Exception {
        channelFilterChain.doFilterConnected(channel);
    }

    @Override
    public void disconnected(TChannel channel) throws Exception {
        channelFilterChain.doFilterDisconnected(channel);
    }

    @Override
    public void received(TChannel channel, Object message) throws Exception {
        channelFilterChain.doFilterReceived(channel, (MessageBean) message);
    }

    @Override
    public void exceptionCaught(TChannel channel, Throwable exception) throws Exception {
        channelFilterChain.doFilterCaught(channel, exception);
    }
}
