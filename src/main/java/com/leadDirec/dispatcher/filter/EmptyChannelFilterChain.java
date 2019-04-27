package com.leadDirec.dispatcher.filter;

import com.leadDirec.net.transport.TChannel;

/**
 * @author xiangdao
 * 空实现
 */
public class EmptyChannelFilterChain  implements ChannelFilterChain{

    /**
     * 单实例
     */
    public static final ChannelFilterChain INSTANCE = new EmptyChannelFilterChain();

    private EmptyChannelFilterChain() {
    }

    @Override
    public void doFilterConnected(TChannel channel) {

    }

    @Override
    public void doFilterDisconnected(TChannel channel) {

    }

    @Override
    public void doFilterReceived(TChannel channel, Object message) {

    }

    @Override
    public void doFilterCaught(TChannel channel, Throwable exception) {

    }
}
