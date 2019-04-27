package com.study.dispatcher.exception;

import com.study.net.transport.TChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExceptionHandler implements ExceptionHandler {

    /**
     * 日志
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * 实例
     */
    public static final ExceptionHandler INSTANCE = new DefaultExceptionHandler();

    /**
     * 禁止外部实例化
     */
    private DefaultExceptionHandler() {
    }


    @Override
    public void exceptionCaught(TChannel channel, Throwable e) {
        if (e != null) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
