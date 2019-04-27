package com.study.dispatcher;

import com.study.common.dto.MessageBean;
import com.study.net.transport.TChannel;

/**
 * @author xiangdao
 */
public interface Dispatcher {

    /**
     *
     * @param channel
     * @param messageBean
     * @throws Exception
     */
    void dispatch(TChannel channel, MessageBean messageBean) throws Exception;

}
