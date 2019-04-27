/**
 * 
 */
package com.study.dispatcher.filter;


import com.study.net.transport.TChannel;

/**
 * channel过滤器
 * 
 * @author xiangdao
 */
public interface ChannelFilter {

    /**
     * 过滤创建连接
     * 
     * @param obj
     * @throws Exception
     */
    public void doFilterConnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception;

    /**
     * 过滤断开连接
     * 
     * @param obj
     * @throws Exception
     */
    public void doFilterDisconnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception;

    /**
     * 过滤接受消息
     * 
     * @param obj
     * @throws Exception
     */
    public void doFilterReceived(TChannel channel, Object message, ChannelFilterChain channelFilterChain)
            throws Exception;

    /**
     * 过滤异常
     * 
     * @param obj
     * @throws Exception
     */
    public void doFilterCaught(TChannel channel, Throwable exception, ChannelFilterChain channelFilterChain)
            throws Exception;

}