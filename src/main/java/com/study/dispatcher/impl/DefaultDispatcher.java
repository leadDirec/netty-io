package com.study.dispatcher.impl;

import com.study.common.dto.MessageBean;
import com.study.dispatcher.Dispatcher;
import com.study.dispatcher.action.ActionProxy;
import com.study.dispatcher.action.ActionProxyFactory;
import com.study.dispatcher.exception.DispatcherException;
import com.study.net.transport.TChannel;

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
