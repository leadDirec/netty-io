package com.leadDirec.common.dto;

public interface MessageBeanFactory {

    /**
     * 通过协议ID获取MessageBean
     *
     * @param protocolId
     * @return
     */
    MessageBean getMessageBean(int protocolId);

}
