package com.leadDirec.dispatcher.filter;

import com.leadDirec.net.transport.TChannel;

public interface ChannelFilterChain {

    /**
     * 执行下一过滤器
     *
     * @param channel
     */
    public void doFilterConnected(TChannel channel);

    /**
     * 执行下一过滤器
     *
     * @param channel
     */
    public void doFilterDisconnected(TChannel channel);

    /**
     * 执行下一过滤器
     *
     * @param channel
     * @param message
     */
    public void doFilterReceived(TChannel channel, Object message);

    /**
     * 执行下一过滤器
     *
     * @param channel
     * @param exception
     */
    public void doFilterCaught(TChannel channel, Throwable exception);

    /**
     * @author jason.z
     *
     */
    public interface Entry {

        /**
         * 获取过滤器
         *
         * @return
         */
        ChannelFilter getChannelFilter();

        /**
         * 获取过滤链
         *
         * @return
         */
        ChannelFilterChain getChannelFilterChain();

    }

}
