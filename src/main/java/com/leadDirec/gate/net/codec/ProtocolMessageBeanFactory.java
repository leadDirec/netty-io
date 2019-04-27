package com.leadDirec.gate.net.codec;

import com.leadDirec.common.dto.MessageBean;
import com.leadDirec.common.dto.MessageBeanFactory;

/**
 * 通信协议Factory
 *
 * @author xiangdao
 *
 */
public class ProtocolMessageBeanFactory implements MessageBeanFactory {

    /**
     * 实例
     */
    private static final ProtocolMessageBeanFactory INSTANCE = new ProtocolMessageBeanFactory();

    /**
     * 构造方法
     */
    private ProtocolMessageBeanFactory() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static final MessageBeanFactory getInstance() {
        return INSTANCE;
    }

    public MessageBean getMessageBean(int protocolId) {
        switch (protocolId) {
            case -0X6FFFFFFF:
                return null;
            default:
                throw new IllegalArgumentException("protocolId:" + Integer.toString(protocolId, 16) + " 对应的protocol不存在");
        }
    }
}
