package com.leadDirec.net.exception;

/**
 * 解码编码异常
 * 
 * @author xiangdao
 *
 */
public class TTCodecException extends Exception {

    /**
     * 序列化版本
     */
    private static final long serialVersionUID = 7713342504776732195L;

    /**
     * 构造方法
     */
    public TTCodecException() {
        super();
    }

    /**
     * 构造方法
     * 
     * @param message
     */
    public TTCodecException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * 
     * @param message
     * @param cause
     */
    public TTCodecException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     * 
     * @param cause
     */
    public TTCodecException(Throwable cause) {
        super(cause);
    }

}
