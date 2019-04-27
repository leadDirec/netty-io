package com.study.net.transport;

/**
 * io客户端
 * 
 * @author xiangdao
 *
 */
public interface TClient extends Endpoint {

    /**
     * 重新连接
     * 
     * @throws Exception
     */
    void reconnect() throws Exception;

}
