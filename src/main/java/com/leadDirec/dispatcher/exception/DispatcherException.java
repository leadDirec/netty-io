package com.leadDirec.dispatcher.exception;

public class DispatcherException extends Exception{

    /**
     * 序列化版本
     */
    private static final long serialVersionUID = 7713342504776732195L;

    /**
     * 构造方法
     */
    public DispatcherException() {
        super();
    }

    /**
     * 构造方法
     *
     * @param message
     */
    public DispatcherException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param message
     * @param cause
     */
    public DispatcherException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     *
     * @param cause
     */
    public DispatcherException(Throwable cause) {
        super(cause);
    }

}
