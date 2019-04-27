package com.leadDirec.dispatcher.filter;

import com.leadDirec.net.transport.TChannel;

public class ChannelFilterAdpater implements ChannelFilter {
    @Override
    public void doFilterConnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception {
        channelFilterChain.doFilterConnected(channel);
    }

    @Override
    public void doFilterDisconnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception {
        channelFilterChain.doFilterDisconnected(channel);
    }

    @Override
    public void doFilterReceived(TChannel channel, Object message, ChannelFilterChain channelFilterChain) throws Exception {
        channelFilterChain.doFilterReceived(channel, message);
    }

    @Override
    public void doFilterCaught(TChannel channel, Throwable exception, ChannelFilterChain channelFilterChain) throws Exception {
        channelFilterChain.doFilterCaught(channel, exception);
    }
}
