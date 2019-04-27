package com.study.net.transport;

/**
 * io服务端
 * 
 * @author xiangdao
 * 
 */
public interface TServer extends Endpoint {

    /**
     * 绑定
     */
    void bind();

    /**
     * 销毁
     */
    void destroy();

}
