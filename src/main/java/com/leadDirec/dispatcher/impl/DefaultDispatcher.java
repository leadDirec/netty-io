package com.leadDirec.dispatcher.impl;

import com.leadDirec.common.dto.MessageBean;
import com.leadDirec.dispatcher.Dispatcher;
import com.leadDirec.dispatcher.action.ActionProxy;
import com.leadDirec.dispatcher.action.ActionProxyFactory;
import com.leadDirec.dispatcher.exception.DispatcherException;
import com.leadDirec.net.transport.TChannel;

/**
 * @author xiangdao
 */
public class DefaultDispatcher implements Dispatcher {

    /**
     *
     */
    private final ActionProxyFactory actionProxyFactory;

    public DefaultDispatcher(ActionProxyFactory actionProxyFactory) {
        this.actionProxyFactory = actionProxyFactory;
    }


    @Override
    public void dispatch(TChannel channel, MessageBean messageBean) throws Exception {
        ActionProxy actionProxy = actionProxyFactory.getActionProxy(messageBean.getProtocolId());
        if (actionProxy == null) {
            throw new DispatcherException("protocolId:" + Integer.toString(messageBean.getProtocolId(), 16)
                    + ",对应的action不存在");
        }
        actionProxy.execute(channel, messageBean);
    }
}
