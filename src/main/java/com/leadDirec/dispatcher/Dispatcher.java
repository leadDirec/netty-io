package com.leadDirec.dispatcher;

import com.leadDirec.common.dto.MessageBean;
import com.leadDirec.net.transport.TChannel;

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
