package com.study.dispatcher.exception;

import com.study.net.transport.TChannel;

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
