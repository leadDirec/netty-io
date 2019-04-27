/**
 * 
 */
package com.leadDirec.dispatcher.action;

/**
 * @author xiangdao
 *
 */
public interface ActionProxyFactory {

    /**
     * 获取actionProxy
     * 
     * @param protocolId
     * @return
     */
    ActionProxy getActionProxy(int protocolId);

}
