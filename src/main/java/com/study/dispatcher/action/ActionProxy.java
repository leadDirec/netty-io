package com.study.dispatcher.action;

import com.study.common.dto.MessageBean;
import com.study.net.transport.TChannel;

public interface ActionProxy {

    /**
     * 获取protocolId
     *
     * @return the protocolId
     */
    public int getProtocolId();

    /**
     * 执行action
     *
     * @param channel
     * @param messageBean
     * @throws Exception
     */
    public void execute(TChannel channel, MessageBean messageBean) throws Exception;


}
