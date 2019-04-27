package com.leadDirec.dispatcher.action.impl;

import com.leadDirec.common.dto.MessageBean;
import com.leadDirec.dispatcher.action.ActionProxy;
import com.leadDirec.net.transport.TChannel;

import java.lang.reflect.Method;

public class DefaultActionProxy implements ActionProxy {

    /**
     * 协议ID
     */
    private int protocolId;

    /**
     * action对象
     */
    private Object action;

    /**
     * 方法
     */
    private Method method;

    @Override
    public int getProtocolId() {
        return protocolId;
    }

    /**
     * 构造方法
     *
     * @param protocolId
     * @param action
     * @param method
     */
    public DefaultActionProxy(int protocolId, Object action, Method method) {
        this.protocolId = protocolId;
        this.action = action;
        this.method = method;
    }

    @Override
    public void execute(TChannel channel, MessageBean messageBean) throws Exception {
        method.invoke(action,channel,messageBean);
    }
}
