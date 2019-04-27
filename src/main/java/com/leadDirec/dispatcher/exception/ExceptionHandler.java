package com.leadDirec.dispatcher.exception;

import com.leadDirec.net.transport.TChannel;

public interface ExceptionHandler {

    /**
     * 发生异常
     *
     * @param channel
     * @param e
     * @throws Exception
     */
    void exceptionCaught(TChannel channel, Throwable e);

}
