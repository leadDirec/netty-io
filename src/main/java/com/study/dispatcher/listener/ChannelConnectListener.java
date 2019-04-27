/**
 * 
 */
package com.study.dispatcher.listener;


import com.study.net.transport.TChannel;

/**
 * Channel监听器,只有过滤器全部通过才会触发监听器
 * 
 * @author xiangdao
 *
 */
public interface ChannelConnectListener {

    /**
     * 连接成功
     * 
     * @param channel
     * @throws Exception
     */
    void connected(TChannel channel) throws Exception;

    /**
     * 断开连接
     * 
     * @param channel
     * @throws Exception
     */
    void disconnected(TChannel channel) throws Exception;

}
